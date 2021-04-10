package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Activity;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.PostingSummary;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.Result;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.ActivityRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.CategoryRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.PostingRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.ResultRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ActivityPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.PostingListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ResultPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.WeeklySummaryListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.mapper.ModelMapper;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.BestType;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.PostingType;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    List<Activity> activities;
    if (offset == 0 && limit == 1) {
      activities = activityRepository.findFirstByOrderByDateAddedDesc();
    } else {
      activities = activityRepository.findAllByOrderByDateAddedDesc();
    }

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

    List<Result> results;
    if (offset == 0 && limit == 1) {
      results = resultRepository.findFirstByOrderByDateAddedDesc();
    } else {
      results = resultRepository.findAllByOrderByDateAddedDesc();
    }

    if (results.size() > 0) {
      ret.setList(results);
      ret.setOffset(offset);
      ret.setSize(results.size());
      ret.setTotal(offset + results.size());
    }

    return ret;
  }

  public PostingListDto<Activity> listBestActivity(BestType bestType, int offset, int limit)
      throws ServerException {

    PostingListDto ret = new PostingListDto();

    List<Activity> activities;
    if (offset == 0 && limit == 1) {
      if (bestType == BestType.count) {
        activities = activityRepository.findFirstByOrderByActivityCountDesc();
      } else {
        activities = activityRepository.findFirstByOrderByPracticeDurationDesc();
      }
    } else {
      if (bestType == BestType.count) {
        activities = activityRepository.findAllByOrderByActivityCountDesc();
      } else {
        activities = activityRepository.findAllByOrderByPracticeDurationDesc();
      }
    }

    if (activities.size() > 0) {
      ret.setList(activities);
      ret.setOffset(offset);
      ret.setSize(activities.size());
      ret.setTotal(offset + activities.size());
    }

    return ret;
  }

  public PostingListDto<Result> listBestResult(BestType bestType, int offset, int limit)
      throws ServerException {

    PostingListDto ret = new PostingListDto();

    List<Result> results;
    if (offset == 0 && limit == 1) {
      if (bestType == BestType.count) {
        results = resultRepository.findFirstByOrderByTestCountDesc();
      } else {
        results = resultRepository.findFirstByOrderByTestDurationDesc();
      }
    } else {
      if (bestType == BestType.count) {
        results = resultRepository.findAllByOrderByTestCountDesc();
      } else {
        results = resultRepository.findAllByOrderByTestDurationDesc();
      }
    }

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
      logger.info("Success to add an result posting[{}]", result);
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

  public WeeklySummaryListDto<ArrayList<Integer>> weeklyActivity(String categoryName,
      BestType bestType) throws ServerException {
    checkCategoryExist(categoryName);

    Timestamp[] tms = getCurrentFrom();

    List<PostingSummary> lastWeekActivities = null;
    if (bestType == BestType.count) {
      lastWeekActivities = activityRepository
          .sumCountByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[0], tms[1]);
    } else if (bestType == BestType.hour) {
      lastWeekActivities = activityRepository
          .sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[0], tms[1]);
    }

    ArrayList<Integer> lastCounts = new ArrayList<>();

    int count = 1; // Sunday is 1
    if (lastWeekActivities.size() == 0) {
      while (count < 8) {
        lastCounts.add(0);
        count++;
      }
    } else {
      for (PostingSummary act : lastWeekActivities) {
        if (count != act.getDateAdded()) { // more than 1
          while (count != act.getDateAdded() && count < 8) {
            lastCounts.add(0);
            count++;
          }
        }
        if (count == act.getDateAdded()) {
          lastCounts.add(act.getCounts());
          count++;
        }
      }

      if (count < 8) { // last is not 7
        while (count < 8) {
          lastCounts.add(0);
          count++;
        }
      }
    }

    List<PostingSummary> currentWeekActivities = null;
    if (bestType == BestType.count) {
      currentWeekActivities = activityRepository
          .sumCountByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[2], tms[3]);
    } else if (bestType == BestType.hour) {
      currentWeekActivities = activityRepository
          .sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[2], tms[3]);
    }

    ArrayList<Integer> currentCounts = new ArrayList<>();
    count = 1; // sunday is 1
    if (currentWeekActivities.size() == 0) {
      while (count < 8) {
        currentCounts.add(0);
        count++;
      }
    } else {
      for (PostingSummary act : currentWeekActivities) {
        if (count != act.getDateAdded()) { // more than 1
          while (count != act.getDateAdded() && count < 8) {
            currentCounts.add(0);
            count++;
          }
        }
        if (count == act.getDateAdded()) {
          currentCounts.add(act.getCounts());
          count++;
        }
      }

      if (count < 8) { // last is not 7
        while (count < 8) {
          currentCounts.add(0);
          count++;
        }
      }
    }

    ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
    lst.add(lastCounts);
    lst.add(currentCounts);
    WeeklySummaryListDto<ArrayList<Integer>> wsl = new WeeklySummaryListDto<>();
    wsl.setList(lst);
    wsl.setType(PostingType.activity);
    wsl.setTotal(lastCounts.size() + currentCounts.size());

    return wsl;
  }

  public WeeklySummaryListDto weeklyResult(String categoryName, BestType bestType) throws
      ServerException {
    checkCategoryExist(categoryName);

    Timestamp[] tms = getCurrentFrom();

    List<PostingSummary> lastWeekResults = null;
    if (bestType == BestType.count) {
      lastWeekResults = resultRepository
          .sumCountByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[0], tms[1]);
    } else if (bestType == BestType.hour) {
      lastWeekResults = resultRepository
          .sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[0], tms[1]);
    }

    ArrayList<Integer> lastCounts = new ArrayList<>();

    int count = 1; // Sunday is 1
    if (lastWeekResults.size() == 0) {
      while (count < 8) {
        lastCounts.add(0);
        count++;
      }
    } else {
      for (PostingSummary act : lastWeekResults) {
        if (count != act.getDateAdded()) { // more than 1
          while (count != act.getDateAdded() && count < 8) {
            lastCounts.add(0);
            count++;
          }
        }
        if (count == act.getDateAdded()) {
          lastCounts.add(act.getCounts());
          count++;
        }
      }

      if (count < 8) { // last is not 7
        while (count < 8) {
          lastCounts.add(0);
          count++;
        }
      }
    }

    List<PostingSummary> currentWeekResults = null;
    if (bestType == BestType.count) {
      currentWeekResults = resultRepository
          .sumCountByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[2], tms[3]);
    } else if (bestType == BestType.hour) {
      currentWeekResults = resultRepository
          .sumDurationByCategoryNameAndWeekdayOfDateAddedBetween(categoryName, tms[2], tms[3]);
    }

    ArrayList<Integer> currentCounts = new ArrayList<>();
    count = 1; // sunday is 1
    if (currentWeekResults.size() == 0) {
      while (count < 8) {
        currentCounts.add(0);
        count++;
      }
    } else {
      for (PostingSummary act : currentWeekResults) {
        if (count != act.getDateAdded()) { // more than 1
          while (count != act.getDateAdded() && count < 8) {
            currentCounts.add(0);
            count++;
          }
        }
        if (count == act.getDateAdded()) {
          currentCounts.add(act.getCounts());
          count++;
        }
      }

      if (count < 8) { // last is not 7
        while (count < 8) {
          currentCounts.add(0);
          count++;
        }
      }
    }

    ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
    lst.add(lastCounts);
    lst.add(currentCounts);
    WeeklySummaryListDto<ArrayList<Integer>> wsl = new WeeklySummaryListDto<>();
    wsl.setList(lst);
    wsl.setType(PostingType.result);
    wsl.setTotal(lastCounts.size() + currentCounts.size());

    return wsl;

  }

  public Timestamp[] getCurrentFrom() {

    LocalDate date = java.time.LocalDate.now();
    int dayOfWeek = date.getDayOfWeek().getValue();
    LocalDate lastWeekFrom = date.minusDays(dayOfWeek + 6);
    LocalDate lastWeekTo = date.minusDays(dayOfWeek - 1);

    LocalDateTime ldtLastWeekFrom = lastWeekFrom.atTime(0, 0, 0, 0);
    LocalDateTime ldtLastWeekTo = lastWeekTo.atTime(0, 0, 0, 0);

    Timestamp tmsLastWeekFrom = Timestamp.valueOf(ldtLastWeekFrom);
    Timestamp tmsLastWeekTo = Timestamp.valueOf(ldtLastWeekTo);

    LocalDate currentWeekFrom = date.minusDays(dayOfWeek - 1);
    LocalDate currentWeekTo = date.plusDays(1);

    LocalDateTime ldtCurrentWeekFrom = currentWeekFrom.atTime(0, 0, 0, 0);
    LocalDateTime ldtCurrentWeekTo = currentWeekTo.atTime(0, 0, 0, 0);

    Timestamp tmsCurrentWeekFrom = Timestamp.valueOf(ldtCurrentWeekFrom);
    Timestamp tmsCurrentWeekTo = Timestamp.valueOf(ldtCurrentWeekTo);

    return new Timestamp[]{tmsLastWeekFrom, tmsLastWeekTo, tmsCurrentWeekFrom, tmsCurrentWeekTo};
  }

}
