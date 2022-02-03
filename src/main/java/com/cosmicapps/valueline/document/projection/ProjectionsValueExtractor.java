package com.cosmicapps.valueline.document.projection;

import io.quarkus.logging.Log;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.jboss.logging.Logger;

public class ProjectionsValueExtractor {

  private static Logger log = Logger.getLogger(ProjectionsValueExtractor.class);

  private List<List<String>> data;

  public ProjectionsValueExtractor(List<List<String>> data) {
    this.data = data;
  }

  public Double get(@NonNull String key) {

    Optional<List<String>> earnings = data.stream()
        .filter(r -> r.get(0)
            .toLowerCase().startsWith(key))
        .findFirst();

    return earnings.isPresent() ? getaDouble(earnings) : 0D;
  }

  private Double getaDouble(Optional<List<String>> earnings) {

    String text = earnings.get().get(1).trim();

    try {
      return Double.valueOf(text);
    } catch (Exception e) {
      Log.warnf("cannot convert [%s] to double", text);
      return 0D;
    }
  }
}
