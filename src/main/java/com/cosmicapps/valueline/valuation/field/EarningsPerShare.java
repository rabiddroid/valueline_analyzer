package com.cosmicapps.valueline.valuation.field;


public class EarningsPerShare extends ValuationPerShareAbstract {

    public static String referenceName = "earnings per sh";
    public static final int RELATIVE_POS_FROM_REVPERSHARE_NAME = 0;

    public EarningsPerShare() {
        super(referenceName, RELATIVE_POS_FROM_REVPERSHARE_NAME);
    }
}
