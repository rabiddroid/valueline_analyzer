package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.HistoricalValue;

import java.util.List;

public interface ValuationField {

    List<HistoricalValue> historicalValues();

    void setHistoricalValues(List<HistoricalValue> value);

    Double projectedValue();

    void setProjectedValue(Double value);

    int relativeDistanceXFromReference();

    String fieldName();

}
