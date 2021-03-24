package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Posting;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {

  Optional<Activity> findByCategoryNameOrderByIdDesc(String categoryName);

  Optional<Activity> findByDateAddedBetween(Timestamp start, Timestamp end);

  Boolean existsById(long id);

  void deleteById(long id);

}
