package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import com.cosmicapps.valueline.document.ProjectionsSupplier;
import com.cosmicapps.valueline.document.projection.Projections;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ProjectionsSupplierTextract implements ProjectionsSupplier {

    private String pdfFileName;
    private final DocumentAnalyzerAws documentAnalyzerAws;

    @Override
    public Projections get() {

        log.info("converting file:{} ...", pdfFileName);
        PdfToImageConverter pdfToImageConverter = new PdfToImageConverter.PdfToImageConverterBuilder()
                .cropBoxLowerLeftX(475).cropBoxLowerLeftY(485)
                .cropBoxUpperRightX(580).cropBoxUpperRightY(590)
                .dpi(500)
                .startPage(1)
                .endPage(1)
                .pdfFileName(pdfFileName)
                .build();

        AnalyzedDocument analyzedDocument = documentAnalyzerAws.analyze(pdfToImageConverter.get().toByteArray());

        return new Projections.ProjectionsBuilder().withAnalyzedDocument(analyzedDocument)
                .build();

    }


    public static class CompanyProjectionsSupplierTextractBuilder {

        private String pdfFileName;
        private final DocumentAnalyzerAws documentAnalyzerAws;

        public CompanyProjectionsSupplierTextractBuilder(DocumentAnalyzerAws documentAnalyzerAws) {
            this.documentAnalyzerAws = documentAnalyzerAws;
        }

        public CompanyProjectionsSupplierTextractBuilder pdfFileName(String value) {

            pdfFileName = value;
            return this;
        }

        public ProjectionsSupplierTextract build() {

            return new ProjectionsSupplierTextract(pdfFileName, documentAnalyzerAws);
        }

    }

}
