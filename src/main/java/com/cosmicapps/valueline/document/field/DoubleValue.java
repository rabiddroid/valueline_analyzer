package com.cosmicapps.valueline.document.field;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

public class DoubleValue {

  private static Logger Log = Logger.getLogger(DoubleValue.class);
  private String text;

  public DoubleValue(String text) {
    this.text = text != null ? text.trim() : text;
  }

  public Double get() {

    if (StringUtils.isEmpty(text)) {
      return null;
    }

    int signed = 1;
    if (text.startsWith("d")) {
      text = text.substring(1);
      signed = -1;
    }

    try {
      return Double.valueOf(text) * signed;
    } catch (NumberFormatException e) {
      Log.warnf("unable to convert to double : %s", text);
      return null;
    }
  }
}
