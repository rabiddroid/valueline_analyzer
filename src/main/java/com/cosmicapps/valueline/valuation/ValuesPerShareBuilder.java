package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.ReferenceFieldLocation;
import com.cosmicapps.valueline.valuation.field.ValuationField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ValuesPerShareBuilder {


    private ValuationField valuationField;
    private List<List<String>> table;
    private int referenceFieldNameRow = -1;
    private int referenceFieldNameCol = -1;

    protected Double getDoubleFromCell(String projectedStringValue) {
        return "".equals(projectedStringValue) ? null : Double.valueOf(projectedStringValue);
    }

    protected void setReferencePoints() {
        ReferenceFieldLocation referenceFieldLocation = ReferenceFieldLocation.find(table);
        referenceFieldNameRow = referenceFieldLocation.getRow();
        referenceFieldNameCol = referenceFieldLocation.getCol();
    }

    protected List<HistoricalValue> getHistoricalValues(Point valuePerShareNameCoordinate) {
        //update historical 5 values
        int latestYearRow = referenceFieldNameRow - 1;
        int latestYearCol = referenceFieldNameCol - 1;
        int latestYear = Integer.valueOf(table.get(latestYearRow).get(latestYearCol));
        List<HistoricalValue> historicalValues = new ArrayList<>();
        int colCount = 6;
        int currentYear = latestYear;
        int currentCol = latestYearCol;

        while (colCount-- > 0) {
            historicalValues.add(HistoricalValue.builder()
                    .year(currentYear--)
                    .value(getDoubleFromCell(table.get(valuePerShareNameCoordinate.x).get(currentCol--))).
                    build());
        }
        return historicalValues;
    }

    protected Double getProjectedValue(Point earningsPerShareNameCoordinate) {
        return getDoubleFromCell(table.get(earningsPerShareNameCoordinate.x).get(earningsPerShareNameCoordinate.y + 1));
    }

    protected Point getValueCoordinates() {
        return new Point(referenceFieldNameRow + valuationField.relativeDistanceXFromReference(), referenceFieldNameCol);
    }


    public ValuesPerShareBuilder with(List<List<String>> table) {
        this.table = table;
        return this;
    }

    public ValuesPerShareBuilder withReferencePointRow(int axis) {
        this.referenceFieldNameRow = axis;
        return this;
    }

    public ValuesPerShareBuilder withReferencePointCol(int axis) {
        this.referenceFieldNameCol = axis;
        return this;
    }

    public <T extends ValuationField> T build(Class<T> fieldType) throws InstantiationException {

        final T valueFieldInstance;

        try {
            valueFieldInstance = fieldType.getDeclaredConstructor().newInstance();
            this.valuationField = valueFieldInstance;
        } catch (Exception e) {
            throw new InstantiationException(e.getMessage());
        }

        if (referenceFieldNameRow < 0 || referenceFieldNameCol < 0) {
            setReferencePoints();
        }

        Point valueFieldCoordinate = getValueCoordinates();


        valueFieldInstance.setHistoricalValues(getHistoricalValues(valueFieldCoordinate));
        valueFieldInstance.setProjectedValue(getProjectedValue(valueFieldCoordinate));


        return valueFieldInstance;

    }


}
