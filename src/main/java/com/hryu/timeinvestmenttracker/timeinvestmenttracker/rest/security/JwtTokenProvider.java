package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final UserRepository userRepository;
  @Value("spring.jwt.secret")
  private String secretKey;
  private long tokenValidMillisecond = 1000L * 60 * 60 * 24 * 7; // 1주일 토큰 유효
  private long adminTokenValidMillisecond = 1000L * 60 * 60 * 24; // 1일 토큰 유효

  @Autowired
  public JwtTokenProvider(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(Authentication authentication, String type) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    Date now = new Date();
    if (type.contains("access_token")) {
      Claims claims = Jwts.claims()
          .setSubject("access_token")
          .setIssuedAt(now) //생성 시간 설정
          .setExpiration(new Date(now.getTime() + tokenValidMillisecond));
      claims.put("USER_NAME_KEY", userPrincipal.getUsername());
      claims.put("USER_ROLE_KEY", userPrincipal.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList()));
      return Jwts.builder()
          .setHeaderParam("typ", "JWT")
          .setClaims(claims)
          .signWith(SignatureAlgorithm.HS256, secretKey)
          .compact();
    } else {
      Claims claims = Jwts.claims()
          .setSubject("admin_token")
          .setIssuedAt(now)
          .setExpiration(new Date(now.getTime() + adminTokenValidMillisecond));
      claims.put("USER_NAME_KEY", userPrincipal.getUsername());
      claims.put("USER_ROLE_KEY", userPrincipal.getAuthorities().stream()
          .map(item -> item.getAuthority())
          .collect(Collectors.toList()));
      return Jwts.builder()
          .setHeaderParam("typ", "ADM_JWT")
          .setClaims(claims)
          .signWith(SignatureAlgorithm.HS256, secretKey)
          .compact();
    }

  }

  public boolean checkToken(String token) {
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token)
          .getBody();
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  public String getUsernameFromToken(String token) {
    String username = (String) Jwts.parser().setSigningKey(secretKey)
        .parseClaimsJws(token).getBody().get("USER_NAME_KEY");
    return username;
  }

}