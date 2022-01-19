package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.ReferenceFieldLocation;
import com.cosmicapps.valueline.valuation.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.valuation.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.valuation.field.EarningsPerShare;
import lombok.Data;

import java.util.List;

@Data
public class ValueLineDocumentBuilder {

    List<List<String>> data;


    public ValueLineDocumentBuilder with(List<List<String>> table) {
        this.data = table;
        return this;
    }


    public ValueLineDocument build() throws InstantiationException {

        ReferenceFieldLocation referenceFieldLocation = ReferenceFieldLocation.find(data);

        return new ValueLineDocument(
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
