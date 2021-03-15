package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "result")
@NoArgsConstructor
@Data
public class Result extends Posting {

  @Column(name = "test_duration", nullable = false)
  private int testDuration;

  @Column(name = "test_count")
  private int testCount;

}
