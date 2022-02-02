package com.cosmicapps.valueline.api;

import com.cosmicapps.valueline.csv.PerformanceMetricsCsv;
import com.cosmicapps.valueline.csv.PerformanceMetricsCsvWriter;
import com.cosmicapps.valueline.domain.PerformanceMetrics;
import com.cosmicapps.valueline.repository.PerformanceMetricsRepository;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.quarkus.panache.common.Sort;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.jboss.logging.Logger;

@Path("/export")
@Consumes(MediaType.APPLICATION_JSON)
public class PerformanceMetricsResource {

  private static final Logger LOG = Logger.getLogger(PerformanceMetricsResource.class);

  @Inject
  private PerformanceMetricsRepository performanceMetricsRepository;

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello RESTEasy";
  }

  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public long count() {
    return performanceMetricsRepository.count();
  }

  @GET
  @Path("/company/performance_metric/{ticker}")
  @Produces(MediaType.APPLICATION_JSON)
  public PerformanceMetrics get(@PathParam("ticker") String ticker) {
    return performanceMetricsRepository.find("ticker", ticker).firstResult();
  }

  @PUT
  @Path("/company/performance_metric")
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  public Response create(PerformanceMetrics performanceMetrics) {
    LOG.info("create/update resource...");
    LOG.debugf("input:%s", performanceMetrics);
    PerformanceMetrics record = get(performanceMetrics.getTicker());
    if (record == null) {
      record = performanceMetrics;
    } else {
      record.setEarningsPerShareLatest(performanceMetrics.getEarningsPerShareLatest());
    }
    performanceMetricsRepository.persist(record);
    return Response.created(URI.create("/company/performance_metric/" + record.id)).build();
  }

  @GET
  @Path("/company/performance_metric/report/tsv")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response getTsv() {
    LOG.info("preparing download resource...");

    StreamingOutput fileStream = outputStream -> {

      try {
        List<PerformanceMetrics> performanceMetrics = performanceMetricsRepository.listAll(Sort.by("ticker", Sort.Direction.Ascending));
        new PerformanceMetricsCsvWriter(outputStream)
            .write(performanceMetrics
                .stream()
                .map(PerformanceMetricsCsv::new)
                .collect(Collectors.toList()));
      } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
        LOG.error("report failed",e);
      }
    };

    return Response
        .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM_TYPE)
        .header("content-disposition", "attachment; filename = report.tsv")
        .build();
  }
}