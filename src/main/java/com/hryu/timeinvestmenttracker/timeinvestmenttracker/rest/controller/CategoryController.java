package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.controller;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.CategoryDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.CategoryListDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.CategoryService;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "posting")
public class CategoryController {

  private final ResponseService responseService;
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(ResponseService responseService, CategoryService categoryService) {
    this.responseService = responseService;
    this.categoryService = categoryService;
  }

  @RequestMapping(value = "/category", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<CategoryListDto> getList(HttpServletRequest request) throws ServerException {
//    if (!Constants.DEV_MODE && !jwtTokenProvider.checkAdminToken(request)) {
//      throw new ServerException(ErrorCode.FAIL_ADMIN_USER_CHECK_BY_INVALID_TOKEN);
//    }

    return responseService.getSingleResult(
        categoryService.list()); // need to add user_id if your logged in.
  }

  @RequestMapping(value = "/category", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult create(
      @RequestBody CategoryDto dto)
      throws ServerException {

    try {

      categoryService.createCategory(dto);
      return CommonResult.SUCCESS_RESPONSE;

    } catch (ServerException e) {
      throw e;
    }
  }

}
