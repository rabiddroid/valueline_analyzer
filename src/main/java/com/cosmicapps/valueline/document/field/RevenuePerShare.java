package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetricName;
import com.cosmicapps.valueline.document.projection.Projections;

public class RevenuePerShare extends ValuationPerShareAbstract {

    public RevenuePerShare(Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.REVENUE,projections,historicalValuations);
    }

    public RevenuePerShare() {
        super(ValuationMetricName.REVENUE, null, null);
    }
}
