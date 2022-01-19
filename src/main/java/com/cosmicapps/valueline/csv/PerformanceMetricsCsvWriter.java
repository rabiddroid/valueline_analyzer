package com.cosmicapps.valueline.csv;

import com.cosmicapps.valueline.analytics.PerformanceMetrics;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class PerformanceMetricsCsvWriter {

    private final Path csvOutputPath;

    public PerformanceMetricsCsvWriter(Path csvOutputPath) {
        this.csvOutputPath = csvOutputPath;
    }

    public void write(List<PerformanceMetricsCsv> performanceMetricsCsv) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try (Writer writer = new FileWriter(csvOutputPath.toFile())) {
            CustomMappingStrategy<PerformanceMetricsCsv> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(PerformanceMetricsCsv.class);
            StatefulBeanToCsv<PerformanceMetricsCsv> statefulBeanToCsv = new StatefulBeanToCsvBuilder<PerformanceMetricsCsv>(writer)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            statefulBeanToCsv.write(performanceMetricsCsv);
        }
    }

}
