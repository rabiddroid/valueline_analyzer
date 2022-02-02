package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.document.TickerName;
import com.cosmicapps.valueline.document.TickerSupplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TickerSupplierTextract implements TickerSupplier {

    private String pdfFileName;
    private final DocumentAnalyzerAws documentAnalyzerAws;

    @Override
    public String get() {


        log.info("converting file:{} ...", pdfFileName);
        PdfToImageConverter pdfToImageConverter = new PdfToImageConverter.PdfToImageConverterBuilder()
                .cropBoxLowerLeftX(40).cropBoxLowerLeftY(700)
                .cropBoxUpperRightX(250).cropBoxUpperRightY(770)
                .dpi(500)
                .startPage(1)
                .endPage(1)
                .pdfFileName(pdfFileName)//TODO:change to relative path from resources
                .build();

        return new TickerName(documentAnalyzerAws.analyze(pdfToImageConverter.get().toByteArray())).get();
    }


    public static class CompanyTickerSupplierTextractBuilder {

        private String pdfFileName;
        private final DocumentAnalyzerAws documentAnalyzerAws;

        public CompanyTickerSupplierTextractBuilder(DocumentAnalyzerAws documentAnalyzerAws) {
            this.documentAnalyzerAws = documentAnalyzerAws;
        }

        public CompanyTickerSupplierTextractBuilder pdfFileName(String value) {

            pdfFileName = value;
            return this;
        }

        public TickerSupplierTextract build() {

            return new TickerSupplierTextract(pdfFileName, documentAnalyzerAws);
        }

    }

}
