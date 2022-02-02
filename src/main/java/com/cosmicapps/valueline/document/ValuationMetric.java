package com.cosmicapps.valueline.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ValuationMetric {

    private int year;
    private Double value;

}
