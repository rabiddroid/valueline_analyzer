package com.cosmicapps.valueline.task;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  ValuelineDataExtraction valuelineDataExtraction;

  @PostConstruct
  public void init() throws IOException {
    Log.debug("setting up staging folder");
    sourceDirName = Paths.get(System.getProperty("user.dir"), stagingFolder);
    if(!Files.isDirectory(sourceDirName)){
      Files.createDirectory(sourceDirName);
    }
  }

  @Scheduled(every = "60s")
  void getStagedFiles() {

    Log.debugf("reading files from directory:%s", sourceDirName);
    try (Stream<Path> stream = Files.list(sourceDirName)) {
      stream
          .filter(file -> !Files.isDirectory(file))
          .filter(p -> p.getFileName().toString().endsWith(".pdf"))
          .forEach(fname -> {
            Log.debugf("file found:%s", fname);
            try {
              valuelineDataExtraction.execute(fname);
            } catch (InstantiationException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
