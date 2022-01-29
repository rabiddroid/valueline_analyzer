package com.cosmicapps.valueline.valuation.projection;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import com.cosmicapps.valueline.valuation.ValuationMetricName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Projections {

  private Map<ValuationMetricName, Double> values;

  public Double getEarningsPerShare() {
    return getValues().get(ValuationMetricName.EARNINGS);
  }

  public Double getDividendsDeclaredPerShare() {
    return getValues().get(ValuationMetricName.DIVIDENDS_DECLARED);
  }

  public Double getAvgAnnualPERatioPerShare() {
    return getValues().get(ValuationMetricName.AVG_ANNUAL_PE);
  }

  public static class ProjectionsBuilder {

    private AnalyzedDocument analyzedDocument;

    public ProjectionsBuilder withAnalyzedDocument(AnalyzedDocument analyzedDocument) {
      this.analyzedDocument = analyzedDocument;
      return this;
    }

    public Projections build() {

      //assuming only 1 table block in analyzed document
      List<List<String>> table = analyzedDocument.getTableValues(
          analyzedDocument.getTables().get(0));
      ProjectionsValueExtractor projectionsValueExtractor = new ProjectionsValueExtractor(table);

      return new Projections(
          Arrays.stream(ValuationMetricName.values())
              .collect(Collectors.toMap(vm -> vm, vm -> projectionsValueExtractor.get(vm.key()))
              ));
    }
  }
}
