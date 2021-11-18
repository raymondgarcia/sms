package com.demo.sms.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse implements Serializable  {

    private static final long serialVersionUID = 1L;
    String name;
    String email;
    LocalDate dayOfBirth;

    public StudentResponse(Long id, String name, String email, LocalDate dayOfBirth) {
        this.name = name;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
    }

    public Integer getAge() {
        return Period.between(dayOfBirth, LocalDate.now()).getYears();
    }
}
