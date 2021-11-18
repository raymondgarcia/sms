package com.demo.sms.student;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/student").toUriString());
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(service.createStudent(student), response);
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentRequest studentRequest) {
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(service.updateStudent(id, student), response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("id") Long id) {
        StudentResponse response = new StudentResponse();
        BeanUtils.copyProperties(service.getStudent(id), response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() {
        List<StudentResponse> response =  new ArrayList<>();
        List<Student> students = service.getStudents();
        students.forEach(student -> {
            StudentResponse studentResponse = new StudentResponse();
            BeanUtils.copyProperties(student, studentResponse);
            response.add(studentResponse);
        });
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        service.deleteStudent(id);
    }
}
