package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Result;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {

  Optional<Activity> findByCategoryNameOrderByIdDesc(String categoryName);

  Optional<Activity> findByDateAddedBetween(Timestamp start, Timestamp end);
}
