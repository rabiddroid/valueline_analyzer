package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import software.amazon.awssdk.services.textract.model.Block;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ValuationTable {

    private AnalyzedDocument analyzedDocument;

    public ValuationTable(AnalyzedDocument analyzedDocument) {
        this.analyzedDocument = analyzedDocument;
    }

    public Optional<Block> valuationTable() {

        List<Block> tables = analyzedDocument.getTables();
        return tables.stream()
                .max(Comparator.comparingInt(b -> b.relationships().get(0).ids().size()));


    }

}
