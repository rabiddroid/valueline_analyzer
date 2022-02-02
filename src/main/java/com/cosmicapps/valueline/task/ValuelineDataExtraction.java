package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.document.ValueLineDocument;
import com.cosmicapps.valueline.domain.PerformanceMetrics;
import com.cosmicapps.valueline.repository.PerformanceMetricsRepository;
import io.quarkus.logging.Log;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ValuelineDataExtraction {

  @Inject
  private ValuationParser valuationParser;
  @Inject
  private ValueLineDataPersistance valueLineDataPersistance;

  public void execute(Path sourceDoc) throws InstantiationException, IOException {

    Path targetDoc = getNewFilePath(sourceDoc);
    Log.debugf("rename file to: [%s] on operation complete", targetDoc.getFileName());
    Files.move(sourceDoc, targetDoc, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

    String targetFileName = targetDoc.toAbsolutePath().toString();
    ValueLineDocument valueLineDocument = valuationParser.parse(targetFileName);

    valueLineDataPersistance.save(valueLineDocument);
  }

  private Path getNewFilePath(Path sourceDoc) {
    return Paths.get(sourceDoc.toAbsolutePath() + ".conv");
  }
}
