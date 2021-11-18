package com.demo.sms.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Name is mandatory")
    String name;

    @NotBlank(message = "Email is mandatory")
    String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Past
    LocalDate dayOfBirth;
}
