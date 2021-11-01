package com.demo.sms.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse implements Serializable  {

    private static final long serialVersionUID = 1L;
    String name;
    String email;
    LocalDate dayOfBirth;
}
