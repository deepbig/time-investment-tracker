package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDetailsImpl implements UserDetails {

  //The serialVersionUID is a universal version identifier for a Serializable class.
  //The 1L has no special meaning. It is just a number that will match 1L as the "other" version id.
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  //The result does not include password.
  @JsonIgnore
  private String password;


  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    //This convert Set<Role> into List<GrantedAuthority> for the Authentication object.
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getPassword(),
        authorities);
  }

  //Class 'UserDetailsImpl' must either implement abstract method 'isAccountNonExpired()' in 'UserDetails'
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //Class 'UserDetailsImpl' must either implement abstract method 'isAccountNonLocked()' in 'UserDetails'
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //Class 'UserDetailsImpl' must either implement abstract method 'isCredentialsNonExpired()' in 'UserDetails'
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //Class 'UserDetailsImpl' must either implement abstract method 'isEnabled()' in 'UserDetails'
  @Override
  public boolean isEnabled() {
    return true;
  }
}
