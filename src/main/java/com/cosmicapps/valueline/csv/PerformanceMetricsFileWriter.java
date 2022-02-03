package com.cosmicapps.valueline.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class PerformanceMetricsFileWriter extends PerformanceMetricsCsvWriter {

  public PerformanceMetricsFileWriter(String outputFilePath) throws FileNotFoundException {
    super(new FileOutputStream(outputFilePath));
  }
}
