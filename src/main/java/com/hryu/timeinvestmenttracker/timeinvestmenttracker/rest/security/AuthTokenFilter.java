package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.Constants;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class AuthTokenFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Autowired private JwtTokenProvider jwtTokenProvider;
  @Autowired private UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String jwt = parseJwt(request);

    // develop mode 일 때 404가 나온다면 로그아웃 후 다시 로그인 하면 됨. production일 때는 정상 동작 함.
    if (Constants.DEV_MODE) {
      if (jwt != null && jwtTokenProvider.checkToken(jwt)) {
        String username = jwtTokenProvider.getUsernameFromToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
      } else if (!request.getServletPath().contains("/auth/")) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
      } else {
        filterChain.doFilter(request, response);
      }
    } else {
      if (jwt != null && jwtTokenProvider.checkToken(jwt)) {
        String username = jwtTokenProvider.getUsernameFromToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      filterChain.doFilter(request, response);
    }

    // Below if block can check all "POST" calls, but it result 405 Unauthorized error. This error
    // type cannot be customized.
    //      if ("POST".equals(request.getMethod()) && !request.getRequestURI().contains("/auth/")) {
    //        if (!jwtTokenProvider.checkAdminToken(request)) throw ServerException {
    //        }
    //      }
  }

  private String parseJwt(HttpServletRequest request) {

    Cookie cookie = WebUtils.getCookie(request, "access_token");
    if (cookie == null) {
      return null;
    }
    String token = cookie.getValue();

    if (StringUtils.hasText(token)) {
      return token;
    }
    return null;
  }
}
