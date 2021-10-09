package com.demo.sms.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<Student> getService() {
        return repository.findAll();
    }

    public Student findByEmail(String email) throws RuntimeException {
        return repository.findStudentByEmail(email).orElseThrow();
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, Student student) {
        Student originalStudent = repository.getById(id);
        originalStudent.setName(student.name);
        originalStudent.setEmail(student.email);
        return repository.save(originalStudent);
    }
}
