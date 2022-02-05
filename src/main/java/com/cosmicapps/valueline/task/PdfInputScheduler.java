package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.csv.PerformanceMetricsFileWriter;
import com.cosmicapps.valueline.domain.PerformanceMetrics;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.quarkus.scheduler.Scheduled;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PdfInputScheduler {

  private Path sourceDirName;
  @ConfigProperty(name = "valueline_data.folder", defaultValue = "valueline_data")
  private String stagingFolder;
  @Inject
  private ValuelineDataExtraction valuelineDataExtraction;
  @Inject
  private AnalyticsReportFileWriter analyticsReportFileWriter;

  @ConfigProperty(name = "data_extraction.show_timing", defaultValue = "false")
  private boolean showTiming;

  @PostConstruct
  public void init() throws IOException {
    Log.debug("setting up staging folder");
    sourceDirName = Paths.get(System.getProperty("user.dir"), stagingFolder);
    if (!Files.isDirectory(sourceDirName)) {
      Files.createDirectory(sourceDirName);
    }
  }

  @Scheduled(every = "60s")
  void getStagedFiles() {

    Log.debugf("reading files from directory:%s", sourceDirName);
    final AtomicBoolean newFilesFound = new AtomicBoolean();
    try (Stream<Path> stream = Files.list(sourceDirName)) {
      stream
          .filter(file -> !Files.isDirectory(file))
          .filter(p -> p.getFileName().toString().endsWith(".pdf"))
          .forEach(fname -> {
            Log.debugf("file found:%s", fname);
            try {

              long startTime = System.nanoTime();

              valuelineDataExtraction.execute(fname);

              if (showTiming) {
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                Log.infof("Extracted data from:%s in %dms%n", fname, duration / 1000000L);
              }

              newFilesFound.set(true);
            } catch (InstantiationException | IOException e) {
              Log.errorf("file operations failed for:%s", fname);
            }
          });
    } catch (IOException e) {
      e.printStackTrace();
    }

    //generate report
    if (newFilesFound.get()) {
      analyticsReportFileWriter.write(sourceDirName);
    }
  }
}
