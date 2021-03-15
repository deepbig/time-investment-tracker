package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity")
@NoArgsConstructor
@Data
public class Activity extends Posting {
  @Column(name = "practice_duration", nullable = false)
  private int practiceDuration;

  @Column(name = "activity_count")
  private int activityCount;

}
