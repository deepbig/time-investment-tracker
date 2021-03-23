package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service;

import com.google.common.collect.Lists;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Category;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.CategoryRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.CategoryDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.CategoryListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.mapper.ModelMapper;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

  private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository,
      ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  public CategoryListDto list() throws ServerException {

    categoryRepository.findByCategoryName("test").orElseThrow(() -> new ServerException(ErrorCode.FAIL_CREATING_CATEGORY_BY_NAME_ALREADY_EXIST));

    CategoryListDto ret = new CategoryListDto();

    List<Category> categories = categoryRepository.findAll();

    if (categories.size() > 0) {
      List<CategoryDto> dtos = Lists.newArrayList();
      categories.forEach(cat -> dtos.add(new CategoryDto().copyFrom(cat)));
      ret.setList(dtos);
    }

    return ret;
  }

  @Transactional
  public void createCategory(CategoryDto dto)
      throws ServerException {

    if (logger.isInfoEnabled()) {
      logger.info("Create an category [{}]", dto);
    }

    if (categoryRepository.existsByCategoryName(dto.getCategoryName()) == true) {
      logger.error(
          "The category[{}] is already exist.",
          dto.getCategoryName());
      ServerException se =
          new ServerException(ErrorCode.FAIL_CREATING_CATEGORY_BY_NAME_ALREADY_EXIST);
      throw se;
    }

    Category category = modelMapper.from(dto);
    categoryRepository.save(category);

    if (logger.isInfoEnabled()) {
      logger.info("Success to add an activity posting[{}]", category);
    }

  }

}
