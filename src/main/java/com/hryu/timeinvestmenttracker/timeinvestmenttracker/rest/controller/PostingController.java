package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.controller;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.PostingService;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//  @RequestMapping(value = "/record", method = RequestMethod.POST)
//  @ResponseStatus(value = HttpStatus.OK)
//  public SingleResult<Long> createRecord(HttpServletRequest request,
//      @RequestBody NewPostingDto node) throws ServerException {
//    if (!Constants.DEV_MODE && !jwtTokenProvider.checkAdminToken(request)) {
//      throw new ServerException(ErrorCode.FAIL_ADMIN_USER_CHECK_BY_INVALID_TOKEN);
//    }
//
//    if (node.getType() == NodeType.SR_MANAGER || node.getType() == NodeType.VC_SERVER) {
//      return responseService.getSingleResult(nodeService.create(node));
//    } else {
//      throw new ServerException(ErrorCode.FAIL_NODE_ADD_BY_NOT_SUPPORTED_NODE_TYPE);
//    }
//  }
//
//  @RequestMapping(value = "/node", method = RequestMethod.GET)
//  @ResponseStatus(value = HttpStatus.OK)
//  public SingleResult<PageDto<NodeDto>> listNodes(
//      @RequestParam(value = "type", required = false) NodeType type,
//      @RequestParam(value = "with_child", defaultValue = "true") boolean withChild,
//      @RequestParam("offset") int offset,
//      @RequestParam("limit") int limit) {
//    return responseService.getSingleResult(nodeService.list(type, withChild, offset, limit));
//  }

}
