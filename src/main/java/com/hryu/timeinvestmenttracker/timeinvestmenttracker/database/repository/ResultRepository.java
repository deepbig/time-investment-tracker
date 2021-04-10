package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.PostingSummary;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Result;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResultRepository extends JpaRepository<Result, Long> {

  List<Result> findAllByOrderByDateAddedDesc();

  Optional<Result> findByCategoryNameOrderByIdDesc(String categoryName);

  Optional<Result> findByDateAddedBetween(Timestamp start, Timestamp end);

  @Query(value = "SELECT DAY_OF_WEEK(p.DATE_ADDED) as DateAdded, SUM(r.TEST_COUNT) as Counts "
      + "FROM POSTINGS p "
      + "INNER JOIN RESULTS r on p.ID=r.ID "
      + "WHERE p.CATEGORY_NAME=?1 "
      + "AND p.DATE_ADDED BETWEEN ?2 AND ?3 "
      + "GROUP BY DAY_OF_WEEK(p.DATE_ADDED)", nativeQuery = true)
  List<PostingSummary> sumCountByCategoryNameAndWeekdayOfDateAddedBetween(String categoryName, Timestamp start, Timestamp end);

  @Query(value = "SELECT DAY_OF_WEEK(p.DATE_ADDED) as DateAdded, SUM(r.TEST_DURATION) as Counts "
      + "FROM POSTINGS p "
      + "INNER JOIN RESULTS r on p.ID=r.ID "
      + "WHERE p.CATEGORY_NAME=?1 "
      + "AND p.DATE_ADDED BETWEEN ?2 AND ?3 "
      + "GROUP BY DAY_OF_WEEK(p.DATE_ADDED)", nativeQuery = true)
  List<PostingSummary> sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(String categoryName, Timestamp start, Timestamp end);

  List<Result> findFirstByOrderByDateAddedDesc();

  List<Result> findFirstByOrderByTestCountDesc();

  List<Result> findAllByOrderByTestCountDesc();

  List<Result> findFirstByOrderByTestDurationDesc();

  List<Result> findAllByOrderByTestDurationDesc();

}
