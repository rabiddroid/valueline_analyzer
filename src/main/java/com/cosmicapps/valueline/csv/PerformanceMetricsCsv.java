package com.cosmicapps.valueline.csv;

import com.cosmicapps.valueline.domain.PerformanceMetrics;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class PerformanceMetricsCsv {

    @CsvBindByName(column = "Ticker")
    @CsvBindByPosition(position = 0)
    private String ticker;
    @CsvBindByName(column = "EPS Latest")
    @CsvBindByPosition(position = 1)
    private Double earningsPerShareLatest;
    @CsvBindByName(column = "Dividend Per Share Current")
    @CsvBindByPosition(position = 2)
    private Double dividendsPerShareCurrent;
    @CsvBindByName(column = "EPS VL Projected")
    @CsvBindByPosition(position = 3)
    private Double earningsPerShareProjected;
    @CsvBindByName(column = "P/E VL Projected")
    @CsvBindByPosition(position = 4)
    private Double priceToEarningsPerShareProjected;
    @CsvBindByName(column = "P/E Lowest 5 yrs")
    @CsvBindByPosition(position = 5)
    private Double priceToEarningsLowestIn5Years;

    public PerformanceMetricsCsv(PerformanceMetrics performanceMetrics) {

        this.ticker = performanceMetrics.getTicker();
        this.earningsPerShareLatest = performanceMetrics.getEarningsPerShareLatest();
        this.dividendsPerShareCurrent = performanceMetrics.getDividendsPerShareCurrent();
        this.earningsPerShareProjected = performanceMetrics.getEarningsPerShareProjected();
        this.priceToEarningsPerShareProjected = performanceMetrics.getPriceToEarningsPerShareProjected();
        this.priceToEarningsLowestIn5Years = performanceMetrics.getPriceToEarningsLowestIn5Years();

    }
}
