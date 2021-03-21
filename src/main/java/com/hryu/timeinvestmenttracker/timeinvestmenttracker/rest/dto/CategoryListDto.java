package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class CategoryListDto<CategoryDto> {
  @JsonProperty("total")
  protected long total;
  @JsonProperty("list")
  protected List<CategoryDto> list;
}
