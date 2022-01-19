package com.cosmicapps.valueline.csv;

import com.cosmicapps.valueline.analytics.PerformanceMetrics;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class PerformanceMetricsCsv {

    @CsvBindByName(column = "Ticker")
    @CsvBindByPosition(position = 0)
    private String ticker;
    @CsvBindByName(column = "EPS Next Year")
    @CsvBindByPosition(position = 1)
    private Double earningsPerShareNextYear;
    @CsvBindByName(column = "Dividend Per Share Latest")
    @CsvBindByPosition(position = 2)
    private Double currentDividendPerShare;
    @CsvBindByName(column = "EPS VL Projected")
    @CsvBindByPosition(position = 3)
    private Double earningsPerShareProjected;
    @CsvBindByName(column = "P/E VL Projected")
    @CsvBindByPosition(position = 4)
    private Double pePerShareProjected;
    @CsvBindByName(column = "P/E Lowest 5 yrs")
    @CsvBindByPosition(position = 5)
    private Double peLowestInLast5Years;

    public PerformanceMetricsCsv(PerformanceMetrics performanceMetrics) {

        this.ticker = performanceMetrics.ticker();
        this.earningsPerShareNextYear = performanceMetrics.earningsPerShareNextYear();
        this.currentDividendPerShare = performanceMetrics.currentDividendPerShare();
        this.earningsPerShareProjected = performanceMetrics.earningsPerShareProjected();
        this.pePerShareProjected = performanceMetrics.pePerShareProjected();
        this.peLowestInLast5Years = performanceMetrics.peLowestInLast5Years();

    }
}
