package com.cosmicapps.valueline.valuation.field;


import com.cosmicapps.valueline.valuation.HistoricalValue;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public abstract class ValuationPerShareAbstract implements ValuationField {

    private List<HistoricalValue> historicalValues;
    private Double projectedValue;
    private String referenceName;
    private int relDistanceXFromReferenceField;

    public ValuationPerShareAbstract(String referenceName, int relDistanceXFromReferenceField) {

        this.referenceName = referenceName;
        this.relDistanceXFromReferenceField = relDistanceXFromReferenceField;
    }

    public List<HistoricalValue> historicalValues() {
        return new ArrayList<>(historicalValues);
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

    public void setHistoricalValues(List<HistoricalValue> historicalValues) {
        this.historicalValues = new ArrayList<>(historicalValues);
    }

    public void setProjectedValue(Double value) {
        this.projectedValue = value != null ? value.doubleValue() : null;
    }
}
