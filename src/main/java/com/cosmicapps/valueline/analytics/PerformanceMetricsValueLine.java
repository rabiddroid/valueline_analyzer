package com.cosmicapps.valueline.analytics;

import com.cosmicapps.valueline.valuation.ValuationMetric;
import com.cosmicapps.valueline.valuation.ValueLineDocument;

import java.util.Comparator;
import java.util.Optional;

public class PerformanceMetricsValueLine implements PerformanceMetrics {

    private final ValueLineDocument valueLineDocument;

    public PerformanceMetricsValueLine(ValueLineDocument valueLineDocument) {
        this.valueLineDocument = valueLineDocument;
    }

    @Override
    public String ticker() {
        return valueLineDocument.getTicker();
    }

    @Override
    public Double earningsPerShareNextYear() {
        return getDoubleValue(valueLineDocument.getEarningsPerShare().valuationMetrics().get(0));
    }

    private Double getDoubleValue(ValuationMetric valuationMetric) {
        return getDoubleValue(valuationMetric.getValue());
    }

    private Double getDoubleValue(Double value) {
        return value != null ? value : 0;
    }

    @Override
    public Double currentDividendPerShare() {
        return getDoubleValue(valueLineDocument.getDividendsDeclaredPerShare().valuationMetrics().get(1));
    }

    @Override
    public Double earningsPerShareProjected() {

        return getDoubleValue(valueLineDocument.getEarningsPerShare().projectedValue());
    }

    @Override
    public Double pePerShareProjected() {
        return getDoubleValue(valueLineDocument.getAvgAnnualPERatioPerShare().projectedValue());
    }

    @Override
    public Double peLowestInLast5Years() {

        Optional<ValuationMetric> min = valueLineDocument.getAvgAnnualPERatioPerShare().valuationMetrics().stream()
                .skip(1)//do not count the last column value
                .filter(h -> h.getValue() != null)
                .limit(5)
                .min(Comparator.comparingDouble(ValuationMetric::getValue));

        return getDoubleValue(min.orElse(null));
    }
}
