package com.cosmicapps.valueline.document;

import com.cosmicapps.valueline.document.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.document.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.document.field.EarningsPerShare;
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
