package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.ReferenceFieldLocation;
import com.cosmicapps.valueline.valuation.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.valuation.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.valuation.field.EarningsPerShare;
import lombok.Data;

import java.util.List;

@Data
public class ValueLineDocumentBuilder {

    private List<List<String>> data;
    private String ticker;


    public ValueLineDocumentBuilder with(List<List<String>> valuationTable) {
        this.data = valuationTable;
        return this;
    }

    public ValueLineDocumentBuilder with(String ticker) {
        this.ticker = ticker;
        return this;
    }


    public ValueLineDocument build() throws InstantiationException {

        ReferenceFieldLocation referenceFieldLocation = ReferenceFieldLocation.find(data);

        return new ValueLineDocument(
                this.ticker,
                new ValuesPerShareBuilder()
                        .with(data)
                        .withReferencePointRow(referenceFieldLocation.getRow())
                        .withReferencePointCol(referenceFieldLocation.getCol())
                        .build(EarningsPerShare.class),
                new ValuesPerShareBuilder()
                        .with(data)
                        .withReferencePointRow(referenceFieldLocation.getRow())
                        .withReferencePointCol(referenceFieldLocation.getCol())
                        .build(DividendsDeclaredPerShare.class),
                new ValuesPerShareBuilder()
                        .with(data)
                        .withReferencePointRow(referenceFieldLocation.getRow())
                        .withReferencePointCol(referenceFieldLocation.getCol())
                        .build(AvgAnnualPERatioPerShare.class)
                );


    }

}
