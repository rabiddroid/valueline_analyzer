package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.valuation.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.valuation.field.EarningsPerShare;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueLineDocument {

    private String ticker;
    private EarningsPerShare earningsPerShare;
    private DividendsDeclaredPerShare dividendsDeclaredPerShare;
    private AvgAnnualPERatioPerShare avgAnnualPERatioPerShare;


}
