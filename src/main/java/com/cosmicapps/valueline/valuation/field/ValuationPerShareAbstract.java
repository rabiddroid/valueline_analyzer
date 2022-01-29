package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetric;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import com.cosmicapps.valueline.valuation.projection.Projections;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
