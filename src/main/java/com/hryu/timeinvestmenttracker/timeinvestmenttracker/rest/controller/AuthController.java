package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.controller;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.CUserNotFoundException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.LoginResultDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.NewPasswordDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.NewUserDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security.JwtTokenProvider;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import io.swagger.annotations.Api;
import java.rmi.ServerException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "auth")
public class AuthController {

  private final ResponseService responseService;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthController(ResponseService responseService,
      AuthenticationManager authenticationManager,
      UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtTokenProvider jwtTokenProvider) {
    this.responseService = responseService;
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult signup(@RequestBody NewUserDto reqUser) throws ServerException {
    if (userRepository.existsByUsername(reqUser.getUsername())) {
      ServerException se = new ServerException(
          ErrorCode.FAIL_USER_SIGNUP_BY_NAME_ALREADY_EXIST.getMsg());
      throw se;
    }

    User user = new User(reqUser.getUsername(),
        passwordEncoder.encode(reqUser.getPassword()));

    userRepository.save(user);

    return CommonResult.SUCCESS_RESPONSE;
  }

  @RequestMapping(value = "/auth/signin/{type}", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<LoginResultDto> signin(@PathVariable("type") String type,
      @RequestBody User reqUser, HttpServletResponse response)
      throws ServerException {

    if (!type.contains("access_token")) {
      throw new ServerException(
          ErrorCode.FAIL_USER_SIGNIN_BY_INVAILD_FORM.getMsg());
    }
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(reqUser.getUsername(), reqUser.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtTokenProvider.createToken(authentication, type);

    if (type.contains("access_token")) {
      Cookie access_cookie = new Cookie("access_token", token);

      access_cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
      access_cookie.setHttpOnly(true);
      access_cookie.setPath("/");

      response.addCookie(access_cookie);
    }

    return responseService.getSingleResult(new LoginResultDto(reqUser.getUsername()));
  }

  @RequestMapping(value = "/auth/logout", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult logout(HttpServletRequest request, HttpServletResponse response)
      throws ServerException {
    Cookie[] cookies = request.getCookies();
    for (Cookie value : cookies) {
      if (value.getName().equals("access_token")) {
        Cookie cookie = new Cookie("access_token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
    }
    return CommonResult.SUCCESS_RESPONSE;
  }

  @RequestMapping(value = "/auth/check", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<LoginResultDto> check(HttpServletRequest request)
      throws ServerException {

    Cookie[] cookies = request.getCookies();
    String username = null;

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("access_token")) {
        username = jwtTokenProvider.getUsernameFromToken(cookie.getValue());
      }
    }
    if (username == null) {
      throw new ServerException(ErrorCode.FAIL_USER_CHECK_BY_TOKEN_NOT_EXIST.getMsg());
    }
    return responseService.getSingleResult(new LoginResultDto(username));
  }

  @RequestMapping(value = "/auth/updatePassword", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult updatePassword(@RequestBody NewPasswordDto password)
      throws ServerException {
    User user = userRepository.findByUsername("admin").orElseThrow(() -> new CUserNotFoundException());

    user.setPassword(passwordEncoder.encode(password.getUpdate()));
    userRepository.save(user);
    return CommonResult.SUCCESS_RESPONSE;
  }
}
