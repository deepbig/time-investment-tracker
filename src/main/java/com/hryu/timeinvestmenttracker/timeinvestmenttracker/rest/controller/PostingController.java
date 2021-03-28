package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.controller;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.Constants;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ActivityPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.PostingListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.ResultPostDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.WeeklySummaryListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.PostingService;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.BestType;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.PostingType;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "posting")
public class PostingController {

  private final ResponseService responseService;
  private final PostingService postingService;

  @Autowired
  public PostingController(ResponseService responseService, PostingService postingService) {
    this.responseService = responseService;
    this.postingService = postingService;
  }

  @RequestMapping(value = "/posting/{type}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<PostingListDto> getPostingList(HttpServletRequest request,
      @PathVariable("type") PostingType type,
      @RequestParam("offset") int offset,
      @RequestParam("limit") int limit) throws ServerException {
//    if (!Constants.DEV_MODE && !jwtTokenProvider.checkAdminToken(request)) {
//      throw new ServerException(ErrorCode.FAIL_ADMIN_USER_CHECK_BY_INVALID_TOKEN);
//    }

    if (type == PostingType.activity) {
      return responseService.getSingleResult(postingService.listActivity(offset, limit));
    } else if (type == PostingType.result) {
      return responseService.getSingleResult(postingService.listResult(offset, limit));
    } else {
      throw new ServerException(ErrorCode.FAIL_GETTING_POSTS_BY_TYPE_NOT_EXISTS);
    }
  }

  @RequestMapping(value = "/posting/{type}/{best_type}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<PostingListDto> getBestPostingList(HttpServletRequest request,
      @PathVariable("type") PostingType type,
      @PathVariable("best_type") BestType bestType,
      @RequestParam("offset") int offset,
      @RequestParam("limit") int limit) throws ServerException {
//    if (!Constants.DEV_MODE && !jwtTokenProvider.checkAdminToken(request)) {
//      throw new ServerException(ErrorCode.FAIL_ADMIN_USER_CHECK_BY_INVALID_TOKEN);
//    }

    if (type == PostingType.activity) {
      return responseService.getSingleResult(postingService.listBestActivity(bestType, offset, limit));
    } else if (type == PostingType.result) {
      return responseService.getSingleResult(postingService.listBestResult(bestType, offset, limit));
    } else {
      throw new ServerException(ErrorCode.FAIL_GETTING_POSTS_BY_TYPE_NOT_EXISTS);
    }
  }

  @RequestMapping(value = "/posting/activity/create", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult createActivityPosting(
      @RequestBody ActivityPostDto dto)
      throws ServerException {

    try {
      postingService.createActivityPosting(dto);
      return CommonResult.SUCCESS_RESPONSE;

    } catch (ServerException e) { throw e; }
  }

  @RequestMapping(value = "/posting/result/create", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult createResultPosting(
      @RequestBody ResultPostDto dto)
      throws ServerException {

    try {
      postingService.createResultPosting(dto);
      return CommonResult.SUCCESS_RESPONSE;

    } catch (ServerException e) { throw e; }
  }


  @RequestMapping(value = "/posting/delete/{post_id}", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult deletePosting(
      HttpServletRequest request,
      @PathVariable long post_id)
      throws ServerException {

    try {
      postingService.deletePosting(post_id);
      return CommonResult.SUCCESS_RESPONSE;

    } catch (ServerException e) { throw e; }
  }

  @RequestMapping(value = "/posting/weekly/{type}/{best_type}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<WeeklySummaryListDto> getWeeklyList(HttpServletRequest request,
      @PathVariable("type") PostingType type,
      @PathVariable("best_type") BestType bestType,
      @RequestParam("category_name") String categoryName) throws ServerException {

    if (type == PostingType.activity) {
      return responseService.getSingleResult(postingService.weeklyActivity(categoryName, bestType));
    } else if (type == PostingType.result) {
      return responseService.getSingleResult(postingService.weeklyResult(categoryName, bestType));
    } else {
      throw new ServerException(ErrorCode.FAIL_GETTING_POSTS_BY_TYPE_NOT_EXISTS);
    }
  }

}
