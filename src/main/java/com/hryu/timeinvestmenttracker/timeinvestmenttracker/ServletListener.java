package com.hryu.timeinvestmenttracker.timeinvestmenttracker;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import java.util.Optional;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@WebListener
public class ServletListener implements ServletContextListener {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    Optional<User> user = userRepository.findByUsername("admin");
    if (!user.isPresent()) {
      User new_user = new User("admin",
          passwordEncoder.encode("admin"));

      userRepository.save(new_user);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // currently nothing to stop after server shutdown.
  }
}