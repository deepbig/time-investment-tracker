package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

  Optional<Activity> findByCategoryNameOrderByIdDesc(String categoryName);

  Optional<Activity> findByDateAddedBetween(Timestamp start, Timestamp end);

  //need to create recent 10 + scroll

}
