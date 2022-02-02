package com.cosmicapps.valueline.document.field;

import com.cosmicapps.valueline.document.HistoricalValuations;
import com.cosmicapps.valueline.document.ValuationMetricName;
import com.cosmicapps.valueline.document.projection.Projections;

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
