package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.valuation.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.valuation.field.EarningsPerShare;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValuationsBuilderTest {

/*    private List<List<String>> table;

    @Before
    public void setUp() throws Exception {


        table = new ArrayList<List<String>>() {
            {

                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "ValueLine PUB. LLC", "24-26"));
                add(Arrays.asList("8.84", "10.18", "11.64", "11.27", "12.48", "14.95", "Revenues per sh A", "19.45"));
                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "Cash Flow per sh", "24-26"));
                add(Arrays.asList("3.48", "4.42", "5.32", "5.04", "5.91", "7.25", "Earnings per sh ABD", "9.7"));
                add(Arrays.asList(".69", ".88", "1.05", "1.22", "1.34", "1.53", "Div'ds Decl'd per sh E", "1.9"));
                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "Capl Spen per sh E", "24-26"));
                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "Book Value per sh E", "24-26"));
                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "Common Shs Outs'd", "24-26"));
                add(Arrays.asList("25.9", "28.4", "29.2", "38.3", "37.5", "", "Avg Ann'l P/E Ratio", "28.0"));
                add(Arrays.asList("2017", "2018", "2019", "2020", "2021", "2022", "Relative P/E Ratio", "1.55"));
            }
        };


    }


    @Test
    public void avgAnnualPERatioPerShare_withTable() throws InstantiationException {


        AvgAnnualPERatioPerShare dividendsDeclaredPerShare = new ValuationsBuilder().with(table).build(AvgAnnualPERatioPerShare.class);
        Assertions.assertThat(dividendsDeclaredPerShare.projectedValue()).isEqualTo(28.0);
        Assertions.assertThat(dividendsDeclaredPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, null), new ValuationMetric(2021, Double.valueOf(37.5)),
                        new ValuationMetric(2020, Double.valueOf(38.3)), new ValuationMetric(2019, Double.valueOf(29.2)),
                        new ValuationMetric(2018, Double.valueOf(28.4)), new ValuationMetric(2017, Double.valueOf("25.9")));

    }

    @Test
    public void avgAnnualPERatioPerShare_withTableAndRowAndCol() throws InstantiationException {


        AvgAnnualPERatioPerShare dividendsDeclaredPerShare = new ValuationsBuilder()
                .with(table)
                .withReferencePointRow(1)
                .withReferencePointCol(6)
                .build(AvgAnnualPERatioPerShare.class);
        Assertions.assertThat(dividendsDeclaredPerShare.projectedValue()).isEqualTo(28.0);
        Assertions.assertThat(dividendsDeclaredPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, null), new ValuationMetric(2021, Double.valueOf(37.5)),
                        new ValuationMetric(2020, Double.valueOf(38.3)), new ValuationMetric(2019, Double.valueOf(29.2)),
                        new ValuationMetric(2018, Double.valueOf(28.4)), new ValuationMetric(2017, Double.valueOf("25.9")));

    }

    @Test
    public void dividendsPerShare_withTable() throws InstantiationException {


        DividendsDeclaredPerShare dividendsDeclaredPerShare = new ValuationsBuilder().with(table).build(DividendsDeclaredPerShare.class);
        Assertions.assertThat(dividendsDeclaredPerShare.projectedValue()).isEqualTo(1.9);
        Assertions.assertThat(dividendsDeclaredPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, Double.valueOf(1.53)), new ValuationMetric(2021, Double.valueOf(1.34)),
                        new ValuationMetric(2020, Double.valueOf(1.22)), new ValuationMetric(2019, Double.valueOf(1.05)),
                        new ValuationMetric(2018, Double.valueOf(0.88)), new ValuationMetric(2017, Double.valueOf("0.69")));

    }

    @Test
    public void dividendsPerShare_withTableAndRowAndCol() throws InstantiationException {


        DividendsDeclaredPerShare dividendsDeclaredPerShare = new ValuationsBuilder().with(table)
                .withReferencePointRow(1)
                .withReferencePointCol(6)
                .build(DividendsDeclaredPerShare.class);
        Assertions.assertThat(dividendsDeclaredPerShare.projectedValue()).isEqualTo(1.9);
        Assertions.assertThat(dividendsDeclaredPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, Double.valueOf(1.53)), new ValuationMetric(2021, Double.valueOf(1.34)),
                        new ValuationMetric(2020, Double.valueOf(1.22)), new ValuationMetric(2019, Double.valueOf(1.05)),
                        new ValuationMetric(2018, Double.valueOf(0.88)), new ValuationMetric(2017, Double.valueOf("0.69")));

    }


    @Test
    public void earningsPerShare_withTable() throws InstantiationException {


        EarningsPerShare earningsPerShare = new ValuationsBuilder().with(table).build(EarningsPerShare.class);
        Assertions.assertThat(earningsPerShare.projectedValue()).isEqualTo(9.7);
        Assertions.assertThat(earningsPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, Double.valueOf(7.25)), new ValuationMetric(2021, Double.valueOf(5.91)),
                        new ValuationMetric(2020, Double.valueOf(5.04)), new ValuationMetric(2019, Double.valueOf(5.32)),
                        new ValuationMetric(2018, Double.valueOf(4.42)), new ValuationMetric(2017, Double.valueOf("3.48")));

    }

    @Test
    public void earningsPerShare_withTableAndRowAndCol() throws InstantiationException {


        EarningsPerShare earningsPerShare = new ValuationsBuilder().with(table)
                .withReferencePointRow(1)
                .withReferencePointCol(6)
                .build(EarningsPerShare.class);
        Assertions.assertThat(earningsPerShare.projectedValue()).isEqualTo(9.7);
        Assertions.assertThat(earningsPerShare.valuationMetrics())
                .containsExactly(new ValuationMetric(2022, Double.valueOf(7.25)), new ValuationMetric(2021, Double.valueOf(5.91)),
                        new ValuationMetric(2020, Double.valueOf(5.04)), new ValuationMetric(2019, Double.valueOf(5.32)),
                        new ValuationMetric(2018, Double.valueOf(4.42)), new ValuationMetric(2017, Double.valueOf("3.48")));

    }*/

    /*    @Test
    public void withTable() {


        RevenuePerShare revenuePerShare = new RevenuePerShareBuilder().with(table).build();
        Assertions.assertThat(revenuePerShare.getProjectedValue()).isEqualTo(19.45);
        Assertions.assertThat(revenuePerShare.getHistoricalValues())
                .containsExactly(new HistoricalValue(2022, Double.valueOf(14.95)), new HistoricalValue(2021, Double.valueOf(12.48)),
                        new HistoricalValue(2020, Double.valueOf(11.27)), new HistoricalValue(2019, Double.valueOf(11.64)),
                        new HistoricalValue(2018, Double.valueOf(10.18)), new HistoricalValue(2017, Double.valueOf("8.84")));

    }

    @Test
    public void withTableAndRowAndCol() {


        RevenuePerShare revenuePerShare = new RevenuePerShareBuilder().with(table)
                .withRevPerShareRow(1)
                .withRevPerShareCol(6)
                .build();
        Assertions.assertThat(revenuePerShare.getProjectedValue()).isEqualTo(19.45);
        Assertions.assertThat(revenuePerShare.getHistoricalValues())
                .containsExactly(new HistoricalValue(2022, Double.valueOf(14.95)), new HistoricalValue(2021, Double.valueOf(12.48)),
                        new HistoricalValue(2020, Double.valueOf(11.27)), new HistoricalValue(2019, Double.valueOf(11.64)),
                        new HistoricalValue(2018, Double.valueOf(10.18)), new HistoricalValue(2017, Double.valueOf("8.84")));

    }*/
}