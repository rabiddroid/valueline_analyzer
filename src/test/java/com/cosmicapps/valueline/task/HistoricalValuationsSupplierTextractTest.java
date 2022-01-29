package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetric;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
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


        Assertions.assertThat(historicalValuations.getValues().get(ValuationMetricName.EARNINGS))
                .containsExactly(new ValuationMetric(2022, Double.valueOf(12.85)), new ValuationMetric(2021, Double.valueOf(11.09   )),
                        new ValuationMetric(2020, Double.valueOf(8.76)), new ValuationMetric(2019, Double.valueOf(8.19)),
                        new ValuationMetric(2018, Double.valueOf(7.09)), new ValuationMetric(2017, Double.valueOf("5.85")));


    }

    @Test
    public void get_honywell_valuations() {


        URL resource = getClass().getClassLoader().getResource("pdf/hon_vl.pdf");

        HistoricalValuations historicalValuations = new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(documentAnalyzer)
            .pdfFileName(resource.getFile())
            .build().get();


        Assertions.assertThat(historicalValuations.getValues().get(ValuationMetricName.AVG_ANNUAL_PE))
            .containsExactly(new ValuationMetric(2022, Double.valueOf(0)), new ValuationMetric(2021, Double.valueOf(27.1)),
                new ValuationMetric(2020, Double.valueOf(23)), new ValuationMetric(2019, Double.valueOf(20.3)),
                new ValuationMetric(2018, Double.valueOf(18.8)), new ValuationMetric(2017, Double.valueOf(18.9)));

    }
}