package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetric;
import com.cosmicapps.valueline.document.ValuationMetricName;
import java.net.URL;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoricalValuationsSupplierTextractIT {

  private DocumentAnalyzerAws documentAnalyzer;

  @BeforeEach
  public void setUp() throws Exception {
    documentAnalyzer = new DocumentAnalyzerAws();
  }

  @Test
  public void get_costco_valuations() {

    URL resource = getClass().getClassLoader().getResource("pdf/cost_vl.pdf");

    HistoricalValuations historicalValuations =
        new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(documentAnalyzer)
            .pdfFileName(resource.getFile())
            .build().get();

    Assertions.assertThat(historicalValuations.getValues().get(ValuationMetricName.EARNINGS))
        .containsExactly(new ValuationMetric(2022, Double.valueOf(12.85)), new ValuationMetric(2021, Double.valueOf(11.09)),
            new ValuationMetric(2020, Double.valueOf(8.76)), new ValuationMetric(2019, Double.valueOf(8.19)),
            new ValuationMetric(2018, Double.valueOf(7.09)), new ValuationMetric(2017, Double.valueOf("5.85")));
  }

  @Test
  public void get_honywell_valuations() {

    URL resource = getClass().getClassLoader().getResource("pdf/hon_vl.pdf");

    HistoricalValuations historicalValuations =
        new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(documentAnalyzer)
            .pdfFileName(resource.getFile())
            .build().get();

    Assertions.assertThat(historicalValuations.getValues().get(ValuationMetricName.AVG_ANNUAL_PE))
        .containsExactly(new ValuationMetric(2022, Double.valueOf(0)), new ValuationMetric(2021, Double.valueOf(27.1)),
            new ValuationMetric(2020, Double.valueOf(23)), new ValuationMetric(2019, Double.valueOf(20.3)),
            new ValuationMetric(2018, Double.valueOf(18.8)), new ValuationMetric(2017, Double.valueOf(18.9)));
  }

  @Test
  public void get_att_valuations() {

    URL resource = getClass().getClassLoader().getResource("pdf/att_vl.pdf");

    HistoricalValuations historicalValuations =
        new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(documentAnalyzer)
            .pdfFileName(resource.getFile())
            .build().get();

    Assertions.assertThat(historicalValuations.getValues().get(ValuationMetricName.AVG_ANNUAL_PE))
        .containsExactly(new ValuationMetric(2022, Double.valueOf(0)), new ValuationMetric(2021, Double.valueOf(0)),
            new ValuationMetric(2020, Double.valueOf(9.8)), new ValuationMetric(2019, Double.valueOf(9.5)),
            new ValuationMetric(2018, Double.valueOf(9.5)), new ValuationMetric(2017, Double.valueOf(12.7)));
  }
}