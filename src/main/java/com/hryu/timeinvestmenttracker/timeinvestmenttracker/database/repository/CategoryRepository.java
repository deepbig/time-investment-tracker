package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByCategoryName(String categoryName);

  Boolean existsByCategoryName(String categoryName);
}

