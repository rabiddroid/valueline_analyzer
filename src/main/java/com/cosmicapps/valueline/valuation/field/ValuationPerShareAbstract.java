package com.cosmicapps.valueline.valuation.field;


import com.cosmicapps.valueline.valuation.ValuationMetric;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public abstract class ValuationPerShareAbstract implements ValuationField {

    private List<ValuationMetric> valuationMetrics;
    private Double projectedValue;
    private String referenceName;
    private int relDistanceXFromReferenceField;

    public ValuationPerShareAbstract(String referenceName, int relDistanceXFromReferenceField) {

        this.referenceName = referenceName;
        this.relDistanceXFromReferenceField = relDistanceXFromReferenceField;
    }

    public List<ValuationMetric> valuationMetrics() {
        return new ArrayList<>(valuationMetrics);
    }

    public Double projectedValue() {
        return projectedValue != null ? projectedValue.doubleValue() : null;
    }

    public int relativeDistanceXFromReference() {
        return relDistanceXFromReferenceField;
    }

    public String fieldName() {
        return referenceName;
    }

    public void setValuationMetrics(List<ValuationMetric> valuationMetrics) {
        this.valuationMetrics = new ArrayList<>(valuationMetrics);
    }

    public void setProjectedValue(Double value) {
        this.projectedValue = value != null ? value.doubleValue() : null;
    }
}
