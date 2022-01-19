package com.cosmicapps.valueline.analytics;

import com.cosmicapps.valueline.valuation.HistoricalValue;
import com.cosmicapps.valueline.valuation.ValueLineDocument;
import com.cosmicapps.valueline.valuation.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.valuation.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.valuation.field.EarningsPerShare;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PerformanceMetricsValueLineTest {

    private ValueLineDocument valueLineDocument;
    private PerformanceMetricsValueLine performanceMetricsValueLine;

    @Before
    public void setUp() throws Exception {


        EarningsPerShare earningsPerShare = createEarnings();
        DividendsDeclaredPerShare dividendPerShare = createDividends();
        AvgAnnualPERatioPerShare peRatioPerShare = createPERatios();
        valueLineDocument = new ValueLineDocument("V",earningsPerShare, dividendPerShare, peRatioPerShare);
        performanceMetricsValueLine = new PerformanceMetricsValueLine(valueLineDocument);

    }

    private AvgAnnualPERatioPerShare createPERatios() {
        AvgAnnualPERatioPerShare peRatios = new AvgAnnualPERatioPerShare();
        peRatios.setProjectedValue(28.0);
        peRatios.setHistoricalValues(Arrays.asList(new HistoricalValue(2022, null), new HistoricalValue(2021, Double.valueOf(37.5)),
                new HistoricalValue(2020, Double.valueOf(38.3)), new HistoricalValue(2019, Double.valueOf(29.2)),
                new HistoricalValue(2018, Double.valueOf(28.4)), new HistoricalValue(2017, Double.valueOf("25.9"))));

        return peRatios;

    }

    private DividendsDeclaredPerShare createDividends() {
        DividendsDeclaredPerShare dividendsDeclaredPerShare = new DividendsDeclaredPerShare();
        dividendsDeclaredPerShare.setProjectedValue(1.9);
        dividendsDeclaredPerShare.setHistoricalValues(Arrays.asList(new HistoricalValue(2022, Double.valueOf(1.53)), new HistoricalValue(2021, Double.valueOf(1.34)),
                new HistoricalValue(2020, Double.valueOf(1.22)), new HistoricalValue(2019, Double.valueOf(1.05)),
                new HistoricalValue(2018, Double.valueOf(0.88)), new HistoricalValue(2017, Double.valueOf("0.69"))));

        return dividendsDeclaredPerShare;

    }

    private EarningsPerShare createEarnings() {
        EarningsPerShare earningsPerShare = new EarningsPerShare();
        earningsPerShare.setProjectedValue(9.7);
        earningsPerShare.setHistoricalValues(Arrays.asList(new HistoricalValue(2022, Double.valueOf(7.25)), new HistoricalValue(2021, Double.valueOf(5.91)),
                new HistoricalValue(2020, Double.valueOf(5.04)), new HistoricalValue(2019, Double.valueOf(5.32)),
                new HistoricalValue(2018, Double.valueOf(4.42)), new HistoricalValue(2017, Double.valueOf("3.48"))));

        return earningsPerShare;
    }

    @Test
    public void earningsPerShareNextYear() {

        Assertions.assertThat(performanceMetricsValueLine.earningsPerShareNextYear())
                .isEqualTo(7.25);
    }

    @Test
    public void currentDividendPerShare() {

        Assertions.assertThat(performanceMetricsValueLine.currentDividendPerShare())
                .isEqualTo(1.34);
    }

    @Test
    public void earningsPerShareProjected() {
        Assertions.assertThat(performanceMetricsValueLine.earningsPerShareProjected())
                .isEqualTo(9.7);
    }

    @Test
    public void pePerShareProjected() {
        Assertions.assertThat(performanceMetricsValueLine.pePerShareProjected())
                .isEqualTo(28.0);
    }

    @Test
    public void peLowestInLast5Years() {
        Assertions.assertThat(performanceMetricsValueLine.peLowestInLast5Years())
                .isEqualTo(25.9);
    }
}