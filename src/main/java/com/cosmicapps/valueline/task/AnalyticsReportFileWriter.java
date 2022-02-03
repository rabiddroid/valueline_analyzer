package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.csv.PerformanceMetricsCsv;
import com.cosmicapps.valueline.csv.PerformanceMetricsFileWriter;
import com.cosmicapps.valueline.domain.PerformanceMetrics;
import com.cosmicapps.valueline.repository.PerformanceMetricsRepository;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnalyticsReportFileWriter {

  @Inject
  private PerformanceMetricsRepository performanceMetricsRepository;

  public void write(Path targetDir) {

    try {
      String reportFileName = targetDir.toAbsolutePath() + "/report.tsv";
      Log.debugf("writing report to file:%s",reportFileName);
      List<PerformanceMetrics> performanceMetrics =
          performanceMetricsRepository.listAll(Sort.by("ticker", Sort.Direction.Ascending));

      new PerformanceMetricsFileWriter(reportFileName).write(
          performanceMetrics
              .stream()
              .map(PerformanceMetricsCsv::new)
              .collect(Collectors.toList())
      );
    } catch (Exception e) {
      Log.error("report failed", e);
    }
  }
}
