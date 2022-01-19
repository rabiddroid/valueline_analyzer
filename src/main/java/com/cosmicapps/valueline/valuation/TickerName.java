package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;

public class TickerName {

    private AnalyzedDocument analyzedDocument;

    public TickerName(AnalyzedDocument analyzedDocument) {
        this.analyzedDocument = analyzedDocument;
    }

    public String get() {

        return analyzedDocument.getLines().stream()
                .filter(b -> b.text().startsWith("NYSE-"))
                .findFirst()
                .map(b -> b.text().split("-")[1])
                .orElse("N/A");


    }

}
