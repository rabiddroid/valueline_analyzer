package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetric;
import com.cosmicapps.valueline.document.ValuationMetricName;
import com.cosmicapps.valueline.document.projection.Projections;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

@ToString
public abstract class ValuationPerShareAbstract implements ValuationField {

  private List<ValuationMetric> valuationMetrics;
  private Double projectedValue;
  private ValuationMetricName metricName;

  public ValuationPerShareAbstract(ValuationMetricName metricName, Projections projections,
      HistoricalValuations historicalValuations) {
    this.metricName = metricName;
    this.projectedValue = projections.getValues().get(metricName);
    this.valuationMetrics = historicalValuations.getValues().get(metricName);
  }

  public List<ValuationMetric> valuationMetrics() {
    return new ArrayList<>(valuationMetrics);
  }

  public Double projectedValue() {
    return projectedValue != null ? projectedValue.doubleValue() : null;
  }


  public void setValuationMetrics(List<ValuationMetric> valuationMetrics) {
    this.valuationMetrics = new ArrayList<>(valuationMetrics);
  }

  public void setProjectedValue(Double value) {
    this.projectedValue = value != null ? value.doubleValue() : null;
  }

  public ValuationMetricName metricName() {
    return metricName;
  }
}
