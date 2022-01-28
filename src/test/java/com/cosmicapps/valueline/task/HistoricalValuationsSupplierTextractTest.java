package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.projection.Projections;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

public class HistoricalValuationsSupplierTextractTest {

    private DocumentAnalyzerAws documentAnalyzer;

    @Before
    public void setUp() throws Exception {
        documentAnalyzer = new DocumentAnalyzerAws();

    }
    @Test
    public void get_costco_valuations() {


        URL resource = getClass().getClassLoader().getResource("pdf/cost_vl.pdf");

        HistoricalValuations historicalValuations = new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(documentAnalyzer)
                .pdfFileName(resource.getFile())
                .build().get();


    }

    @Test
    public void get_apple_valuations() {


        URL resource = getClass().getClassLoader().getResource("pdf/aapl_vl.pdf");

        Projections projections = new ProjectionsSupplierTextract.CompanyProjectionsSupplierTextractBuilder(documentAnalyzer)
                .pdfFileName(resource.getFile())
                .build().get();


        Assertions.assertThat(projections.getEarningsPerShare()).isEqualTo(8.3);
        Assertions.assertThat(projections.getDividendsDeclaredPerShare()).isEqualTo(1.8);
        Assertions.assertThat(projections.getAvgAnnualPERatioPerShare()).isEqualTo(22);

    }
}