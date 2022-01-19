package com.cosmicapps.valueline;

import com.cosmicapps.valueline.analytics.PerformanceMetricsValueLine;
import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import com.cosmicapps.valueline.csv.PerformanceMetricsCsv;
import com.cosmicapps.valueline.csv.PerformanceMetricsCsvWriter;
import com.cosmicapps.valueline.valuation.TickerName;
import com.cosmicapps.valueline.valuation.ValuationTable;
import com.cosmicapps.valueline.valuation.ValueLineDocument;
import com.cosmicapps.valueline.valuation.ValueLineDocumentBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class App {

    public static void main(String[] args) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    <sourceDoc> \n\n" +
                "Where:\n" +
                "    sourceDoc - the path where the document is located (must be an image, for example, C:/AWS/book.png). \n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String sourceDoc = args[0];
        String destinationDoc = args[1];
        Region region = Region.US_WEST_1;
        TextractClient textractClient = TextractClient.builder()
                .region(region)
                .build();

        final ValueLineDocument valueLineDocument;
        try {
            valueLineDocument = analyzeDoc(textractClient, sourceDoc);
        } finally {
            log.trace("closing aws client...");
            textractClient.close();
        }

        log.debug("writing csv...");
        new PerformanceMetricsCsvWriter(Paths.get(destinationDoc)).write(Arrays.asList(new PerformanceMetricsCsv(new PerformanceMetricsValueLine(valueLineDocument))));

    }

    // snippet-start:[textract.java2._analyze_doc.main]
    public static ValueLineDocument analyzeDoc(TextractClient textractClient, String sourceDoc) {

        try {

            log.debug("reading source file...");
            InputStream sourceStream = new FileInputStream(new File(sourceDoc));
            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

            // Get the input Document object as bytes
            Document myDoc = Document.builder()
                    .bytes(sourceBytes)
                    .build();

            List<FeatureType> featureTypes = new ArrayList<>();
            featureTypes.add(FeatureType.TABLES);

            AnalyzeDocumentRequest analyzeDocumentRequest = AnalyzeDocumentRequest.builder()
                    .featureTypes(featureTypes)
                    .document(myDoc)
                    .build();

            log.debug("analyzing file...");
            AnalyzeDocumentResponse analyzeDocument = textractClient.analyzeDocument(analyzeDocumentRequest);
            List<Block> docInfo = analyzeDocument.blocks();
            AnalyzedDocument analyzedDocument = new AnalyzedDocument(docInfo);
            //analyzedDocument.printLines();


            log.debug("extracting information...");
            Optional<Block> valuationTableOptional = new ValuationTable(analyzedDocument).valuationTable();
            if (!valuationTableOptional.isPresent()) {
                throw new IllegalArgumentException("Required Data table missing: Valuation Data");
            }
            return new ValueLineDocumentBuilder()
                    .with(analyzedDocument.getTableValues(valuationTableOptional.get()))
                    .with(new TickerName(analyzedDocument).get())
                    .build();

            //System.out.println(valueLineDocument);


        } catch (TextractException | FileNotFoundException | InstantiationException e) {

            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public static void DisplayBlockInfo(Block block) {
        System.out.println("Block Id : " + block.id());
        if (block.text() != null)
            System.out.println("    Detected text: " + block.text());
        System.out.println("    Type: " + block.blockType());

        if (block.blockType().equals("PAGE") != true) {
            System.out.println("    Confidence: " + block.confidence().toString());
        }
        if (block.blockType().equals("CELL")) {
            System.out.println("    Cell information:");
            System.out.println("        Column: " + block.columnIndex());
            System.out.println("        Row: " + block.rowIndex());
            System.out.println("        Column span: " + block.columnSpan());
            System.out.println("        Row span: " + block.rowSpan());

        }

        System.out.println("    Relationships");
        List<Relationship> relationships = block.relationships();
        if (relationships != null) {
            for (Relationship relationship : relationships) {
                System.out.println("        Type: " + relationship.type());
                System.out.println("        IDs: " + relationship.ids().toString());
            }
        } else {
            System.out.println("        No related Blocks");
        }

        System.out.println("    Geometry");
        System.out.println("        Bounding Box: " + block.geometry().boundingBox().toString());
        System.out.println("        Polygon: " + block.geometry().polygon().toString());

        List<EntityType> entityTypes = block.entityTypes();

        System.out.println("    Entity Types");
        if (entityTypes != null) {
            for (EntityType entityType : entityTypes) {
                System.out.println("        Entity Type: " + entityType);
            }
        } else {
            System.out.println("        No entity type");
        }
        if (block.page() != null)
            System.out.println("    Page: " + block.page());
        System.out.println();
    }
}
