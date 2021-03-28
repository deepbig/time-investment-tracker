package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activities")
@NoArgsConstructor
@Data
public class Activity extends Posting {
//public class Activity {

  @Column(name = "practice_duration", nullable = false)
  private int practiceDuration;

  @Column(name = "activity_count")
  private int activityCount;

}
