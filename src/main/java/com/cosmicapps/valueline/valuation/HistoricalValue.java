package com.cosmicapps.valueline.valuation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricalValue {

    private int year;
    private Double value;

}
