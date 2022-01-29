package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import com.cosmicapps.valueline.valuation.projection.Projections;

public class RevenuePerShare extends ValuationPerShareAbstract {

    public RevenuePerShare(Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.REVENUE,projections,historicalValuations);
    }

    public RevenuePerShare() {
        super(ValuationMetricName.REVENUE, null, null);
    }
}
