package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Category;
import lombok.Data;

@Data
public class CategoryDto {

  @JsonProperty(value = "id", required = true)
  private Long id;

  @JsonProperty(value = "category_name", required = true)
  private String categoryName;

  public CategoryDto copyFrom(Category src) {
    this.id = src.getId();
    this.categoryName = src.getCategoryName();
    return this;
  }

}
