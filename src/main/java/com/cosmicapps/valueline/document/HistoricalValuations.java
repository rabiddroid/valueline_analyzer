package com.cosmicapps.valueline.document;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import com.cosmicapps.valueline.document.field.DoubleValue;
import io.quarkus.logging.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jboss.logging.Logger;

@Data
@AllArgsConstructor
public class HistoricalValuations {

  private static final Logger Log = Logger.getLogger(HistoricalValuations.class);
  private Map<ValuationMetricName, List<ValuationMetric>> values;

  public static class HistoricalValuationsBuilder {
    private AnalyzedDocument analyzedDocument;

    public HistoricalValuationsBuilder withAnalyzedDocument(AnalyzedDocument analyzedDocument) {
      this.analyzedDocument = analyzedDocument;
      return this;
    }

    /**
     * build the last known 6 years of data
     *
     * @return
     */
    public HistoricalValuations build() {

      //assuming only 1 table block in analyzed document
      List<List<String>> table = analyzedDocument.getTableValues(analyzedDocument.getTables().get(0));
      List<List<String>> values = table.subList(1, table.size());
      List<String> years = table.get(0);
      Map<ValuationMetricName, List<ValuationMetric>> valuations = new HashMap<>();
      int colStart = values.get(0).size() - 1;
      int colCount = colStart - 6;
      int colEnd = colCount < 0 ? 0 : colCount;

      //for each enumerated value find row index
      //parse each col for values and create values list
      //add list to map

      for (ValuationMetricName name : ValuationMetricName.values()) {
        Optional<List<String>> optionalRow = findRow(name, colStart, values);
        List<ValuationMetric> valuationMetrics = Collections.emptyList();
        if (optionalRow.isPresent()) {
          List<String> row = optionalRow.get();
          valuationMetrics = new ArrayList<>();
          for (int col = colStart - 1; col >= colEnd; col--) {
            valuationMetrics.add(new ValuationMetric(Integer.valueOf(years.get(col)), getaDouble(row.get(col))));
          }
        }
        valuations.put(name, valuationMetrics);
      }

      return new HistoricalValuations(valuations);
    }

    /*
    Find the first row that matches metric name and return row index
     */
    private Optional<List<String>> findRow(ValuationMetricName name, int fieldNameCol, List<List<String>> values) {

      return values.stream()
          .filter(r -> name.matches(r.get(fieldNameCol)))
          .findFirst();
    }

    private Double getaDouble(String value) {
      return new DoubleValue(value).get();
    }
  }
}
