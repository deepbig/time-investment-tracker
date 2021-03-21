package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Result;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {

  List<Result> findAllByOrderByDateAddedDesc();

  Optional<Result> findByCategoryNameOrderByIdDesc(String categoryName);

  Optional<Result> findByDateAddedBetween(Timestamp start, Timestamp end);
}
