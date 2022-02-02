package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetricName;
import com.cosmicapps.valueline.document.projection.Projections;

public class DividendsDeclaredPerShare extends ValuationPerShareAbstract {

  public DividendsDeclaredPerShare(
      Projections projections,
      HistoricalValuations historicalValuations) {
    super(ValuationMetricName.DIVIDENDS_DECLARED, projections, historicalValuations);
  }

  public DividendsDeclaredPerShare() {
    super(ValuationMetricName.DIVIDENDS_DECLARED, null, null);
  }
}
