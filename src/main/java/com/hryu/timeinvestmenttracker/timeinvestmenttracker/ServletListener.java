package com.hryu.timeinvestmenttracker.timeinvestmenttracker;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.RoleType;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebListener
public class ServletListener implements ServletContextListener {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Context Started");

    WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
        .getAutowireCapableBeanFactory().autowireBean(this);

    Optional<User> user = userRepository.findByUsername("tester");
    if (!user.isPresent()) {
      User new_user = new User("tester",
          passwordEncoder.encode("tester123"));

      Set<RoleType> roles = new HashSet<>();
      roles.add(RoleType.ROLE_ADMIN);
      roles.add(RoleType.ROLE_USER);

      new_user.setRoles(roles);
      userRepository.save(new_user);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("Context Destroyed");
  }
}