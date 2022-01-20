package com.cosmicapps.valueline.valuation.field;

public class RevenuePerShare extends ValuationPerShareAbstract {

    public static String referenceName = "revenues per sh";
    private static final int RELATIVE_POS_FROM_REVPERSHARE_NAME = -2;

    public RevenuePerShare() {
        super(referenceName, RELATIVE_POS_FROM_REVPERSHARE_NAME);
    }
}
