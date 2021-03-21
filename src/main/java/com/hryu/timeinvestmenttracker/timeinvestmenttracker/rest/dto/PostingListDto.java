package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class PostingListDto<T> {
  @JsonProperty("offset")
  protected long offset;
  @JsonProperty("size")
  protected long size;
  @JsonProperty("total")
  protected long total;
  @JsonProperty("list")
  protected List<T> list;
}
