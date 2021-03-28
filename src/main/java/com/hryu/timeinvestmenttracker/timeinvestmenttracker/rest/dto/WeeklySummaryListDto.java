package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.PostingType;
import java.util.List;
import lombok.Data;

@Data
public class WeeklySummaryListDto<T> {
  @JsonProperty("type")
  protected PostingType type;
  @JsonProperty("total")
  protected long total;
  @JsonProperty("list")
  protected List<T> list;
}
