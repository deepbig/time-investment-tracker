package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "results")
@NoArgsConstructor
@Data
public class Result extends Posting {

  @Column(name = "test_duration", nullable = false)
  private int testDuration;

  @Column(name = "test_count")
  private int testCount;

}
