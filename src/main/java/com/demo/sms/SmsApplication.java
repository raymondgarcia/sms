package com.demo.sms;

import com.demo.sms.student.Student;
import com.demo.sms.student.StudentRepository;
import com.demo.sms.user.AppUser;
import com.demo.sms.user.Role;
import com.demo.sms.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class SmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, UserService userService) {
		return args -> {
			Student dummy = new Student(1L,
					"dummy",
					"dummy@dummy.com", LocalDate.of(2000, Month.JANUARY, 5));

			Student dummy1 = new Student(2L,
					"dummy1",
					"dummy1@dummy.com", LocalDate.of(2000, Month.JANUARY, 5));

			repository.saveAll(List.of(dummy, dummy1));


			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null,"user1", "user1", "1234", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"user2", "user2", "1234", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"user3", "user3", "1234", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"user4", "user4", "1234", new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"user5", "user5", "1234", new ArrayList<Role>()));

			userService.addRoleToUser("user1", "ROLE_USER");
			userService.addRoleToUser("user1", "ROLE_MANAGER");
			userService.addRoleToUser("user1", "ROLE_ADMIN");

			userService.addRoleToUser("user2", "ROLE_USER");
			userService.addRoleToUser("user2", "ROLE_MANAGER");

			userService.addRoleToUser("user3", "ROLE_SUPER_ADMIN");

			userService.addRoleToUser("user4", "ROLE_SUPER_ADMIN");
		};
	}

}
