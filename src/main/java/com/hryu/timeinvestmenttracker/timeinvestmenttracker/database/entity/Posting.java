package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.RoleType;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posting")
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

  @Column(name = "date_added")
  private Timestamp dateAdded;

}