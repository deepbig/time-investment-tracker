package com.hryu.timeinvestmenttracker.timeinvestmenttracker;

//import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity.User;
//import com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TimeInvestmentTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(TimeInvestmentTrackerApplication.class, args);
	}

}
