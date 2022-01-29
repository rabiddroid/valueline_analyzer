package com.cosmicapps.valueline.valuation.field;

import com.cosmicapps.valueline.valuation.HistoricalValuations;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import com.cosmicapps.valueline.valuation.projection.Projections;

public class AvgAnnualPERatioPerShare extends ValuationPerShareAbstract {

    public AvgAnnualPERatioPerShare(
        Projections projections,
        HistoricalValuations historicalValuations) {
        super(ValuationMetricName.AVG_ANNUAL_PE,projections,historicalValuations);
    }

    public AvgAnnualPERatioPerShare() {
        super(ValuationMetricName.AVG_ANNUAL_PE, null, null);
    }
}
