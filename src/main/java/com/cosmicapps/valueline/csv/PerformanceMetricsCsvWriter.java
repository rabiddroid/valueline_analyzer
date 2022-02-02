package com.cosmicapps.valueline.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class PerformanceMetricsCsvWriter {

  private final OutputStream outputStream;

  public PerformanceMetricsCsvWriter(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void write(List<PerformanceMetricsCsv> performanceMetrics)
      throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

    try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

      CustomMappingStrategy<PerformanceMetricsCsv> mappingStrategy =
          new CustomMappingStrategy<>();
      mappingStrategy.setType(PerformanceMetricsCsv.class);
      StatefulBeanToCsv<PerformanceMetricsCsv>
          statefulBeanToCsv = new StatefulBeanToCsvBuilder<PerformanceMetricsCsv>(writer)
          .withMappingStrategy(mappingStrategy)
          .withSeparator('\t')
          .build();

      statefulBeanToCsv.write(performanceMetrics);
    }
  }
}
