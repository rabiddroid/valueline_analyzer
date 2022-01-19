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

        int refRow = -1;
        int refCol = -1;
        String refText = null;


        for (int row = 0; row < table.size(); row++) {
            List<String> rowLine = table.get(row);
            for (int col = 0; col < rowLine.size(); col++) {
                String cellValue = rowLine.get(col);
                if (cellValue.toLowerCase().startsWith(referenceName)) {
                    refRow = row;
                    refCol = col;
                    refText = cellValue;
                    break;
                }
            }
        }


        return new ReferenceFieldLocation(refRow, refCol, refText);
    }

    public static ReferenceFieldLocation find(List<List<String>> table) {
        return find(RevenuePerShare.referenceName, table);
    }
}
