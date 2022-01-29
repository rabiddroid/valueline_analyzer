package com.cosmicapps.valueline.valuation;

import com.cosmicapps.valueline.aws.textract.AnalyzedDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Data
@AllArgsConstructor
public class HistoricalValuations {

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


            for (List<String> row : values) {
                String valueName = row.get(colStart);
                Optional<ValuationMetricName> valuationMetricNameOptional = ValuationMetricName.find(valueName);
                if (valuationMetricNameOptional.isEmpty())
                    continue;

                List<ValuationMetric> valuationMetrics = new ArrayList<>();
                for (int col = colStart - 1; col >= colEnd; col--) {
                    valuationMetrics.add(new ValuationMetric(Integer.valueOf(years.get(col)), getaDouble(row.get(col))));
                }
                valuations.put(valuationMetricNameOptional.get(), valuationMetrics);
            }

            return new HistoricalValuations(valuations);
        }

        private Double getaDouble(String value) {
            return StringUtils.isNotEmpty(value) ? Double.valueOf(value) : 0D;
        }

    }
}
