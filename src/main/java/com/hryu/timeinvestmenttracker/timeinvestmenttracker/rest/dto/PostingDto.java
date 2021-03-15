package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostingDto {

  @JsonProperty(value = "id", required = true)
  private Long id;

  @JsonProperty(value = "content")
  private String content;

  @JsonProperty(value = "category_name", required = true)
  private String categoryName;

  @JsonProperty(value = "date_added", required = true)
  private String dateAdded;
}
