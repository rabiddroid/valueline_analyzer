package com.cosmicapps.valueline.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PerformanceMetrics extends PanacheEntity {

  private String ticker;
  private Double earningsPerShareLatest;
  private Double dividendsPerShareCurrent;
  private Double earningsPerShareProjected;
  private Double priceToEarningsPerShareProjected;
  private Double priceToEarningsLowestIn5Years;
}