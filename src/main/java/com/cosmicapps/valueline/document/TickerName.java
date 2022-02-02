package com.cosmicapps.valueline.document;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;

public class TickerName {

    private AnalyzedDocument analyzedDocument;

    public TickerName(AnalyzedDocument analyzedDocument) {
        this.analyzedDocument = analyzedDocument;
    }

    public String get() {

        return analyzedDocument.getWords().stream()
                .filter(b -> b.text().startsWith("NYSE-") || b.text().startsWith("NDQ-"))
                .findFirst()
                .map(b -> b.text().split("-")[1])
                .orElse("N/A");


    }

}
