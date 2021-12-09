package com.demo.sms;

import com.demo.sms.student.Student;
import com.demo.sms.student.StudentRepository;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
		return new KeycloakSpringBootConfigResolver();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			List<Student> list = new ArrayList<>();
			for (long id = 1; id < 100; id++) {
				Student dummy = new Student(id,
						"dummy",
						"dummy"+id+"@dummy.com", LocalDate.of(2000, Month.JANUARY, 5));
				list.add(dummy);
			}
			repository.saveAll(list);

		};
	}

}
