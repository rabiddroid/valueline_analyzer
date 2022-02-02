package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.ValuationMetric;
import com.cosmicapps.valueline.document.ValuationMetricName;
import java.util.List;

public interface ValuationField {

    List<ValuationMetric> valuationMetrics();

    void setValuationMetrics(List<ValuationMetric> value);

    Double projectedValue();

    void setProjectedValue(Double value);

    ValuationMetricName metricName();

}
