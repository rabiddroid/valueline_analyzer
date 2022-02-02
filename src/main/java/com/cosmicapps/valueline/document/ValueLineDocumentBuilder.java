package com.cosmicapps.valueline.document;

import com.cosmicapps.valueline.document.field.AvgAnnualPERatioPerShare;
import com.cosmicapps.valueline.document.field.DividendsDeclaredPerShare;
import com.cosmicapps.valueline.document.field.EarningsPerShare;
import com.cosmicapps.valueline.document.projection.Projections;
import lombok.Data;

@Data
public class ValueLineDocumentBuilder {

  private String ticker;
  private HistoricalValuations historicalValuations;
  private Projections projections;

  public ValueLineDocumentBuilder with(Projections projections) {
    this.projections = projections;
    return this;
  }

  public ValueLineDocumentBuilder with(HistoricalValuations historicalValuations) {
    this.historicalValuations = historicalValuations;
    return this;
  }

  public ValueLineDocumentBuilder with(String ticker) {
    this.ticker = ticker;
    return this;
  }

  public ValueLineDocument build() throws InstantiationException {

    return new ValueLineDocument(
        this.ticker,
        new EarningsPerShare(projections, historicalValuations),
        new DividendsDeclaredPerShare(projections, historicalValuations),
        new AvgAnnualPERatioPerShare(projections, historicalValuations)
    );
  }
}
