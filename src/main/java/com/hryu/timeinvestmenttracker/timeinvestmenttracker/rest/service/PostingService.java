package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Result;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.ActivityRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.CategoryRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.PostingRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.ResultRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ActivityPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.PostingListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ResultPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.mapper.ModelMapper;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostingService {

  private static final Logger logger = LoggerFactory.getLogger(PostingService.class);

  private final PostingRepository postingRepository;
  private final ActivityRepository activityRepository;
  private final ResultRepository resultRepository;
  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public PostingService(PostingRepository postingRepository, ActivityRepository activityRepository,
      ResultRepository resultRepository, CategoryRepository categoryRepository,
      ModelMapper modelMapper) {
    this.postingRepository = postingRepository;
    this.activityRepository = activityRepository;
    this.resultRepository = resultRepository;
    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  public PostingListDto<Activity> listActivity(int offset, int limit)
      throws ServerException {

    PostingListDto ret = new PostingListDto();

    List<Activity> activities = activityRepository.findAllByOrderByDateAddedDesc();

    if (activities.size() > 0) {
      ret.setList(activities);
      ret.setOffset(offset);
      ret.setSize(activities.size());
      ret.setTotal(offset + activities.size());
    }

    return ret;
  }

  public PostingListDto<Result> listResult(int offset, int limit)
      throws ServerException {

    PostingListDto ret = new PostingListDto();

    List<Result> results = resultRepository.findAllByOrderByDateAddedDesc();

    if (results.size() > 0) {
      ret.setList(results);
      ret.setOffset(offset);
      ret.setSize(results.size());
      ret.setTotal(offset + results.size());
    }

    return ret;
  }

  @Transactional
  public void createActivityPosting(ActivityPostDto dto)
      throws ServerException {

    if (logger.isInfoEnabled()) {
      logger.info("Create an activity posting [{}]", dto);
    }

    checkCategoryExist(dto.getCategoryName());

    Activity activity = modelMapper.from(dto);
    activityRepository.save(activity); //여기서 시간 넣지 뭐.

    if (logger.isInfoEnabled()) {
      logger.info("Success to add an activity posting[{}]", activity);
    }
  }

  @Transactional
  public void createResultPosting(ResultPostDto dto)
      throws ServerException {

    if (logger.isInfoEnabled()) {
      logger.info("Create an result posting [{}]", dto);
    }

    checkCategoryExist(dto.getCategoryName());

    Result result = modelMapper.from(dto);
    resultRepository.save(result);

    if (logger.isInfoEnabled()) {
      logger.info("Success to add an activity posting[{}]", result);
    }
  }

  @Transactional
  public void deletePosting(long post_id)
      throws ServerException {

    if (logger.isInfoEnabled()) {
      logger.info("Delete selected posting id [{}]", post_id);
    }

    if (postingRepository.existsById(post_id) == false) {
      logger.error(
          "The posting id [{}] cannot be found.",
          post_id);
      ServerException se =
          new ServerException(ErrorCode.FAIL_DELETING_POST_BY_ID_NOT_FOUND);
      throw se;
    }

    postingRepository.deleteById(post_id);

    if (logger.isInfoEnabled()) {
      logger.info("Success to delete an posting[{}]", post_id);
    }
  }

  public void checkCategoryExist(String categoryName) throws ServerException {
    if (categoryRepository.existsByCategoryName(categoryName) == false) {
      logger.error(
          "The category[{}] of posting cannot be found.",
          categoryName);
      ServerException se =
          new ServerException(ErrorCode.FAIL_CREATING_POST_BY_CATEGORY_NOT_EXISTS);
      throw se;
    }
  }
}
