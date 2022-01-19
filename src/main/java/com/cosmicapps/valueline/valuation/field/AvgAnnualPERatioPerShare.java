package com.cosmicapps.valueline.valuation.field;

public class AvgAnnualPERatioPerShare extends ValuationPerShareAbstract {

    private static String referenceName = "avg ann'l p/e ratio";
    private static final int RELATIVE_POS_FROM_REVPERSHARE_NAME = 7;

    public AvgAnnualPERatioPerShare() {
        super(referenceName, RELATIVE_POS_FROM_REVPERSHARE_NAME);
    }
}
