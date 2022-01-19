package com.cosmicapps.valueline.valuation.field;

public class DividendsDeclaredPerShare extends ValuationPerShareAbstract {

    private static String referenceName = "div'ds decl'd per sh";
    private static final int RELATIVE_POS_FROM_REVPERSHARE_NAME = 3;

    public DividendsDeclaredPerShare() {
        super(referenceName, RELATIVE_POS_FROM_REVPERSHARE_NAME);
    }
}
