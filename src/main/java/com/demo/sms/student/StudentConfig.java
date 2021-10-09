package com.demo.sms.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
                return args -> {
                    Student dummy = new Student(1L,
                            "dummy",
                            "dummy@dummy.com", LocalDate.of(2000, Month.JANUARY, 5));

                    Student dummy1 = new Student(2L,
                            "dummy1",
                            "dummy1@dummy.com", LocalDate.of(2000, Month.JANUARY, 5));

                    repository.saveAll(List.of(dummy, dummy1));
                };
    }
}
