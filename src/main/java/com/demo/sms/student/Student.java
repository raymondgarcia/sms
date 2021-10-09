package com.demo.sms.student;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;
    String name;
    String email;
    LocalDate dayOfBirth;

    public Student(Long id, String name, String email, LocalDate dayOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
    }

    public Integer getAge() {
        return Period.between(dayOfBirth, LocalDate.now()).getYears();
    }

}
