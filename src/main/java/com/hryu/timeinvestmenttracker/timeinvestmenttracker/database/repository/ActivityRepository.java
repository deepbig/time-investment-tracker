package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.PostingSummary;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

  List<Activity> findAllByOrderByDateAddedDesc();

  Optional<Activity> findByCategoryNameOrderByIdDesc(String categoryName);

  List<Activity> findByCategoryNameAndDateAddedBetween(String categoryName, Timestamp start, Timestamp end);


  @Query(value = "SELECT DAY_OF_WEEK(p.DATE_ADDED) as DateAdded, SUM(a.ACTIVITY_COUNT) as Counts "
      + "FROM POSTINGS p "
      + "INNER JOIN ACTIVITIES a on p.ID=a.ID "
      + "WHERE p.CATEGORY_NAME=?1 "
      + "AND p.DATE_ADDED BETWEEN ?2 AND ?3 "
      + "GROUP BY DAY_OF_WEEK(p.DATE_ADDED)", nativeQuery = true)
  List<PostingSummary> sumCountByCategoryNameAndWeekdayOfDateAddedBetween(String categoryName, Timestamp start, Timestamp end);

  @Query(value = "SELECT DAY_OF_WEEK(p.DATE_ADDED) as DateAdded, SUM(a.PRACTICE_DURATION) as Counts "
      + "FROM POSTINGS p "
      + "INNER JOIN ACTIVITIES a on p.ID=a.ID "
      + "WHERE p.CATEGORY_NAME=?1 "
      + "AND p.DATE_ADDED BETWEEN ?2 AND ?3 "
      + "GROUP BY DAY_OF_WEEK(p.DATE_ADDED)", nativeQuery = true)
  List<PostingSummary> sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(String categoryName, Timestamp start, Timestamp end);

  List<Activity> findFirstByOrderByDateAddedDesc();

  List<Activity> findFirstByOrderByActivityCountDesc();

  List<Activity> findAllByOrderByActivityCountDesc();

  List<Activity> findFirstByOrderByPracticeDurationDesc();

  List<Activity> findAllByOrderByPracticeDurationDesc();

  //need to create recent 10 + scroll

//  @Query("SELECT distinct e.source FROM Edge e WHERE e.task=?1 AND e.type=?2 AND e.srcRole=?3")
//  List<Long> findAllSourceNodeIdsByTaskAndTypeAndSrcType(Long task, TaskType type, NodeRole role);

}
