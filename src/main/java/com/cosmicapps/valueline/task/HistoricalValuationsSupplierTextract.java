package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.HistoricalValuationsSupplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class HistoricalValuationsSupplierTextract implements HistoricalValuationsSupplier {

    private String pdfFileName;
    private final DocumentAnalyzerAws documentAnalyzerAws;

    @Override
    public HistoricalValuations get() {

        log.info("converting file:{} ...", pdfFileName);
        PdfToImageConverter pdfToImageConverter = new PdfToImageConverter.PdfToImageConverterBuilder()
                .cropBoxLowerLeftX(40).cropBoxLowerLeftY(485)
                .cropBoxUpperRightX(550).cropBoxUpperRightY(590)
                .dpi(500)
                .startPage(1)
                .endPage(1)
                .pdfFileName(pdfFileName)
                .build();

        AnalyzedDocument analyzedDocument = documentAnalyzerAws.analyze(pdfToImageConverter.get().toByteArray());

        return new HistoricalValuations.HistoricalValuationsBuilder().withAnalyzedDocument(analyzedDocument)
                .build();

    }


    public static class HistoricalValuationsSupplierTextractBuilder {

        private String pdfFileName;
        private final DocumentAnalyzerAws documentAnalyzerAws;

        public HistoricalValuationsSupplierTextractBuilder(DocumentAnalyzerAws documentAnalyzerAws) {
            this.documentAnalyzerAws = documentAnalyzerAws;
        }

        public HistoricalValuationsSupplierTextractBuilder pdfFileName(String value) {

            pdfFileName = value;
            return this;
        }

        public HistoricalValuationsSupplierTextract build() {

            return new HistoricalValuationsSupplierTextract(pdfFileName, documentAnalyzerAws);
        }

    }

}
