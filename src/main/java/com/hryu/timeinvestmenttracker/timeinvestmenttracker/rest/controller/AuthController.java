package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.controller;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.CEmailSigninFailedException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.LoginResultDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.NewPasswordDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto.NewUserDto;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security.JwtTokenProvider;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security.UserDetailsImpl;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.RoleType;
import io.swagger.annotations.Api;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
  @PreAuthorize("hasRole('ADMIN')")
  @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult signup(@RequestBody NewUserDto reqUser) throws ServerException {
    if (userRepository.existsByUsername(reqUser.getUsername())) {
      ServerException se = new ServerException(
          ErrorCode.FAIL_USER_SIGNUP_BY_NAME_ALREADY_EXIST);
//      actionService.failed(ActionCategory.USER_SIGNUP, null, se);
      throw se;
    }

    User user = new User(reqUser.getUsername(),
        passwordEncoder.encode(reqUser.getPassword()));

    Set<String> strRoles = reqUser.getRole();
    Set<RoleType> roles = new HashSet<>();

    if (strRoles == null) {
      roles.add(RoleType.ROLE_USER);
    } else {
      strRoles.forEach(role -> {
        if ("ADMIN".equals(role)) {
          roles.add(RoleType.ROLE_ADMIN);
        } else {
          roles.add(RoleType.ROLE_USER);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

//    actionService.act(ActionCategory.USER_SIGNUP, user.getId(),
//        "Success to add an user : " + user.toString());
    return CommonResult.SUCCESS_RESPONSE;
  }

  @RequestMapping(value = "/auth/signin/{type}", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<LoginResultDto> signin(@PathVariable("type") String type,
      @RequestBody User reqUser, HttpServletResponse response)
      throws ServerException {

    if (!type.contains("access_token") && !type.contains("admin_token")) {
      throw new ServerException(
          ErrorCode.FAIL_USER_SIGNIN_BY_INVAILD_FORM);
    }
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(reqUser.getUsername(), reqUser.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    String token = jwtTokenProvider.createToken(authentication, type);

    if (type.contains("access_token")) {
      Cookie access_cookie = new Cookie("access_token", token);

      access_cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
      access_cookie.setHttpOnly(true);
      access_cookie.setPath("/");

      response.addCookie(access_cookie);
    } else {
      User user = userRepository.findByUsername(reqUser.getUsername()).orElseThrow(
          CEmailSigninFailedException::new);

      user.setAdminToken(token);
      userRepository.save(user);

      Cookie admin_cookie = new Cookie("admin_token", token);

      admin_cookie.setMaxAge(7 * 24 * 60 * 60);
      admin_cookie.setHttpOnly(true);
      admin_cookie.setPath("/");

      response.addCookie(admin_cookie);
    }

    return responseService.getSingleResult(new LoginResultDto(reqUser.getUsername(), roles));
  }

  @RequestMapping(value = "/auth/logout", method = RequestMethod.POST)
  @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
      } else if (value.getName().equals("admin_token")) {
        Cookie cookie = new Cookie("admin_token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
    }
    return CommonResult.SUCCESS_RESPONSE;
  }

  @RequestMapping(value = "/auth/check", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public SingleResult<LoginResultDto> check(HttpServletRequest request)
      throws ServerException {

    Cookie[] cookies = request.getCookies();
    String username = null;
    List<String> roles = null;

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("access_token")) {
        username = jwtTokenProvider.getUsernameFromToken(cookie.getValue());
        roles = jwtTokenProvider.getUserRoleFromToken(cookie.getValue());
      }
    }
    if (username == null || roles == null) {
      throw new ServerException(ErrorCode.FAIL_USER_CHECK_BY_TOKEN_NOT_EXIST);
    }
    return responseService.getSingleResult(new LoginResultDto(username, roles));
  }

  @RequestMapping(value = "/auth/updatePassword", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMIN')")
  @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
  @ResponseStatus(value = HttpStatus.OK)
  public CommonResult updatePassword(@RequestBody NewPasswordDto password)
      throws ServerException {
    User user = userRepository.findByUsername("admin").orElseThrow(
        CEmailSigninFailedException::new);
    user.setPassword(passwordEncoder.encode(password.getUpdate()));
    userRepository.save(user);
    return CommonResult.SUCCESS_RESPONSE;
  }
}