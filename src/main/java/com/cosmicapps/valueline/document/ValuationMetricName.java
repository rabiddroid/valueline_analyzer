package com.cosmicapps.valueline.document;

import java.util.Arrays;
import java.util.Optional;

public enum ValuationMetricName {

    EARNINGS("earnings"), DIVIDENDS_DECLARED("div'ds decl"), AVG_ANNUAL_PE("avg ann'l p/e"), REVENUE("revenues");

    private String key;

    ValuationMetricName(String key) {
        this.key = key;
    }

    public static boolean is(String text) {
        return Arrays.stream(values()).anyMatch(v -> text.toLowerCase().trim().startsWith(v.key));
    }

    public static Optional<ValuationMetricName> find(String name) {
        return Arrays.stream(values())
                .filter(v->name.toLowerCase().trim().startsWith(v.key))
                .findFirst();
    }


    public String key(){
        return this.key;
    }
}
