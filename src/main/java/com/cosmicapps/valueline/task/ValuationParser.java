package com.cosmicapps.valueline.task;

import com.cosmicapps.valueline.document.ValueLineDocument;
import com.cosmicapps.valueline.document.ValueLineDocumentBuilder;
import io.quarkus.logging.Log;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValuationParser {


  public ValueLineDocument parse(String fileName) throws InstantiationException {

    DocumentAnalyzerAws documentAnalyzer = new DocumentAnalyzerAws();
    Log.debugf("analyze and parse file [%s]...",fileName);

    //TODO:split file operations into async tasks
    return  new ValueLineDocumentBuilder()
        .with(new TickerSupplierTextract.CompanyTickerSupplierTextractBuilder(documentAnalyzer)
            .pdfFileName(fileName)
            .build().get())
        .with(new ProjectionsSupplierTextract.CompanyProjectionsSupplierTextractBuilder(
            documentAnalyzer)
            .pdfFileName(fileName)
            .build().get())
        .with(new HistoricalValuationsSupplierTextract.HistoricalValuationsSupplierTextractBuilder(
            documentAnalyzer)
            .pdfFileName(fileName)
            .build().get())
        .build();

  }









}
