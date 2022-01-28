package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Data
@AllArgsConstructor
public class HistoricalValuations {

    private Map<String, List<ValuationMetric>> values;

    public static class HistoricalValuationsBuilder {
        private AnalyzedDocument analyzedDocument;

        public HistoricalValuationsBuilder withAnalyzedDocument(AnalyzedDocument analyzedDocument) {
            this.analyzedDocument = analyzedDocument;
            return this;
        }

        public HistoricalValuations build() {

            //assuming only 1 table block in analyzed document
            List<List<String>> table = analyzedDocument.getTableValues(analyzedDocument.getTables().get(0));
            List<List<String>> values = table.subList(1, table.size());
            List<String> years = table.get(0);
            Map<String, List<ValuationMetric>> valuations = new HashMap<>();

            for (List<String> row : values) {
                int col = row.size() - 1;
                String valueName = row.get(col);
                Optional<ValuationMetricName> valuationMetricNameOptional = ValuationMetricName.find(valueName);

                if (valuationMetricNameOptional.isEmpty())
                    continue;

                List<ValuationMetric> valuationMetrics = new ArrayList<>();
                for (col--; col >= 0; col--) {
                    valuationMetrics.add(new ValuationMetric(Integer.valueOf(years.get(col)), getaDouble(row.get(col))));
                }
                valuations.put(valuationMetricNameOptional.get().name(), valuationMetrics);
            }

            return new HistoricalValuations(valuations);
        }

        private Double getaDouble(String value) {
            return StringUtils.isNotEmpty(value) ? Double.valueOf(value) : null;
        }

    }
}
