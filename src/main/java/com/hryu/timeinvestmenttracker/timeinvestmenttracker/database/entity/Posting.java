package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "postings")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
public class Posting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "content")
  private String content;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "date_added", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Timestamp dateAdded;

//  @ManyToMany
//  @JoinTable(name = "PostingActivity", joinColumns = { @JoinColumn(name = "postingId", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "activityId", referencedColumnName = "id") })
//  private Set activity = new HashSet();
//
//  @ManyToMany
//  @JoinTable(name = "PostingResult", joinColumns = { @JoinColumn(name = "postingId", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "resultId", referencedColumnName = "id") })
//  private Set result = new HashSet();

}
