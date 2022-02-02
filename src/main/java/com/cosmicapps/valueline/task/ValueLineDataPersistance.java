package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.document.ValuationMetric;
import com.cosmicapps.valueline.document.ValueLineDocument;
import com.cosmicapps.valueline.domain.PerformanceMetrics;
import com.cosmicapps.valueline.repository.PerformanceMetricsRepository;
import io.quarkus.logging.Log;
import java.util.Comparator;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ValueLineDataPersistance {

  @Inject
  private PerformanceMetricsRepository performanceMetricsRepository;

  @Transactional
  void save(ValueLineDocument document) {

    PerformanceMetrics entity = create(document);
    Log.debug("save data...");
    performanceMetricsRepository.persist(entity);
  }

  //TODO: can be its own mapper class
  private PerformanceMetrics create(ValueLineDocument document) {

    PerformanceMetrics result =
        performanceMetricsRepository.find("ticker", document.getTicker()).firstResult();

    if (result == null) {
      result = new PerformanceMetrics();
    }

    result.setTicker(document.getTicker());
    result.setEarningsPerShareLatest(getDoubleValue(document.getEarningsPerShare().valuationMetrics().get(0)));
    result.setDividendsPerShareCurrent(getDoubleValue(document.getDividendsDeclaredPerShare().valuationMetrics().get(1)));
    result.setEarningsPerShareProjected(document.getEarningsPerShare().projectedValue());
    result.setPriceToEarningsPerShareProjected(document.getAvgAnnualPERatioPerShare().projectedValue());
    result.setPriceToEarningsLowestIn5Years(getLowestIn5Years(document));

    return result;
  }

  private Double getLowestIn5Years(ValueLineDocument document) {
    Optional<ValuationMetric>
        min = document.getAvgAnnualPERatioPerShare().valuationMetrics().stream()
        .skip(1)//do not count the last column value
        .filter(h -> h.getValue() != null)
        .limit(5)
        .min(Comparator.comparingDouble(ValuationMetric::getValue));

    return getDoubleValue(min.orElse(null));
  }

  private Double getDoubleValue(ValuationMetric valuationMetric) {
    return getDoubleValue(valuationMetric.getValue());
  }

  private Double getDoubleValue(Double value) {
    return value != null ? value : 0;
  }
}
