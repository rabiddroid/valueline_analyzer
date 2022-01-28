package com.cosmicapps.valueline.task;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

public class TickerSupplierTextractTest {


    private DocumentAnalyzerAws documentAnalyzer;

    @Before
    public void setUp() throws Exception {
        documentAnalyzer = new DocumentAnalyzerAws();

    }

    @Test
    public void name_costco() {

        URL resource = getClass().getClassLoader().getResource("pdf/cost_vl.pdf");

        String ticker = new TickerSupplierTextract.CompanyTickerSupplierTextractBuilder(documentAnalyzer)
                .pdfFileName(resource.getFile())
                .build().get();


        Assertions.assertThat(ticker).isEqualTo("COST");

    }

    @Test
    public void name_honeywell() {

        URL resource = getClass().getClassLoader().getResource("pdf/hon_vl.pdf");

        String ticker = new TickerSupplierTextract.CompanyTickerSupplierTextractBuilder(documentAnalyzer)
                .pdfFileName(resource.getFile())
                .build().get();


        Assertions.assertThat(ticker).isEqualTo("HON");

    }
}