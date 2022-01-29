package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.ValuationMetric;
import com.cosmicapps.valueline.valuation.ValuationMetricName;

import java.util.List;

public interface ValuationField {

    List<ValuationMetric> valuationMetrics();

    void setValuationMetrics(List<ValuationMetric> value);

    Double projectedValue();

    void setProjectedValue(Double value);

    ValuationMetricName metricName();

}
