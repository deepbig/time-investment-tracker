package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

  private final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  //This method will be triggered anytime unauthenticated User requests a secured HTTP resource and an AuthenticationException is thrown.
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    logger.error("Unauthorized error: {}", authException.getMessage());
    //HttpServletResponse.SC_UNAUTHORIZED is the 401 Status Code.
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
  }
}
