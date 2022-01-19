package com.cosmicapps.valueline.analytics;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;


public interface PerformanceMetrics {

    String ticker();
    Double earningsPerShareNextYear();
    Double currentDividendPerShare();
    Double earningsPerShareProjected();
    Double pePerShareProjected();
    Double peLowestInLast5Years();

}
