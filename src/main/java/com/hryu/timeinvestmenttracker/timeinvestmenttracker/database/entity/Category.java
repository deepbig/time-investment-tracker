package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "category_name")
    })
@NoArgsConstructor
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "category_name", length = 100, unique = true, nullable = false)
  private String categoryName;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
