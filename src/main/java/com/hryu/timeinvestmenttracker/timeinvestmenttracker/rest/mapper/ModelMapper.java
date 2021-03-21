package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.mapper;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Category;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ActivityPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.CategoryDto;
import lombok.NonNull;
import org.modelmapper.Converter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ModelMapper {

  private static final org.modelmapper.ModelMapper secondaryMapper = new org.modelmapper.ModelMapper();

  public ActivityPostDto from(@NonNull Activity src) {
    return secondaryMapper.map(src, ActivityPostDto.class);
  }

  public Activity from(@NonNull ActivityPostDto src) {
    return secondaryMapper.map(src, Activity.class);
  }

  public Category from(@NonNull CategoryDto src) {
    return secondaryMapper.map(src, Category.class);
  }

}
