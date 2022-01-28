package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.valuation.field.ReferenceFieldLocation;
import com.cosmicapps.valueline.valuation.field.ValuationField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ValuesPerShareBuilder {


    public static final int REFERENCE_ROW_TO_YEAR_DISTANCE = -3;//TODO: NEED A MORE CONSTANT WAY TO CALCULATE YEAR ROW/COL
    public static final int REFERENCE_COL_TO_YEAR_DISTANCE = -1;
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

    protected List<ValuationMetric> getHistoricalValues(Point valuePerShareNameCoordinate) {
        //update historical 5 values
        int latestYearRow = referenceFieldNameRow + REFERENCE_ROW_TO_YEAR_DISTANCE;
        int latestYearCol = referenceFieldNameCol + REFERENCE_COL_TO_YEAR_DISTANCE;
        int latestYear = Integer.valueOf(table.get(latestYearRow).get(latestYearCol));
        List<ValuationMetric> valuationMetrics = new ArrayList<>();
        int colCount = 6;
        int currentYear = latestYear;
        int currentCol = latestYearCol;

        while (colCount-- > 0) {
            valuationMetrics.add(ValuationMetric.builder()
                    .year(currentYear--)
                    .value(getDoubleFromCell(table.get(valuePerShareNameCoordinate.x).get(currentCol--))).
                    build());
        }
        return valuationMetrics;
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


        valueFieldInstance.setValuationMetrics(getHistoricalValues(valueFieldCoordinate));
        valueFieldInstance.setProjectedValue(getProjectedValue(valueFieldCoordinate));


        return valueFieldInstance;

    }


}
