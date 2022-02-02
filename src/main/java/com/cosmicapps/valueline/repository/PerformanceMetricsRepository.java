package com.cosmicapps.valueline.repository;

import com.cosmicapps.valueline.domain.PerformanceMetrics;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PerformanceMetricsRepository implements PanacheRepository<PerformanceMetrics> {
}
