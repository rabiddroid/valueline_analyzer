package com.cosmicapps.valueline.valuation.field;


import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import com.cosmicapps.valueline.valuation.projection.Projections;

public class EarningsPerShare extends ValuationPerShareAbstract {

    public EarningsPerShare(Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.EARNINGS,projections,historicalValuations);
    }

    public EarningsPerShare() {
        super(ValuationMetricName.EARNINGS, null, null);
    }
}
