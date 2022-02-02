package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetricName;
import com.cosmicapps.valueline.document.projection.Projections;

public class EarningsPerShare extends ValuationPerShareAbstract {

    public EarningsPerShare(Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.EARNINGS,projections,historicalValuations);
    }

    public EarningsPerShare() {
        super(ValuationMetricName.EARNINGS, null, null);
    }
}
