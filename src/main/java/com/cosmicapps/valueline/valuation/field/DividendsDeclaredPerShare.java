package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import com.cosmicapps.valueline.valuation.projection.Projections;

public class DividendsDeclaredPerShare extends ValuationPerShareAbstract {

    public DividendsDeclaredPerShare(
        Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.DIVIDENDS_DECLARED,projections,historicalValuations);
    }

    public DividendsDeclaredPerShare() {
        super(ValuationMetricName.DIVIDENDS_DECLARED, null, null);
    }
}
