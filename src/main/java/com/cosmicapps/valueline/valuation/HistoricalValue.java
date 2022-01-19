package com.cosmicapps.valueline.valuation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HistoricalValue {

    private int year;
    private Double value;

}
