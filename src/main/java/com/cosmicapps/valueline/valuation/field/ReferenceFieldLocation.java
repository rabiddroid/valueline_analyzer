package com.cosmicapps.valueline.valuation.field;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReferenceFieldLocation {

    private int row;
    private int col;
    private String columnText;


    public static ReferenceFieldLocation find(String referenceName, List<List<String>> table) {


        for (int row = 0; row < table.size(); row++) {
            List<String> rowLine = table.get(row);
            for (int col = 0; col < rowLine.size(); col++) {
                String cellValue = rowLine.get(col);
                if (cellValue.toLowerCase().startsWith(referenceName)) {
                    return new ReferenceFieldLocation(row, col, cellValue);
                }
            }
        }

        throw new IllegalStateException("reference data point:" + referenceName + "not found");

    }

    public static ReferenceFieldLocation find(List<List<String>> table) {
        return find(EarningsPerShare.referenceName, table);
    }
}
