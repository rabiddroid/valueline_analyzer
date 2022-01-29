package com.cosmicapps.valueline.valuation.projection;


import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public class ProjectionsValueExtractor {

    private List<List<String>> data;

    public ProjectionsValueExtractor(List<List<String>> data) {
        this.data = data;
    }

    public Double get(@NonNull String key) {

        Optional<List<String>> earnings = data.stream()
                .filter(r -> r.get(0)
                        .toLowerCase().startsWith(key))
                .findFirst();

        return earnings.isPresent() ? Double.valueOf(earnings.get().get(1)) : 0D;

    }


}
