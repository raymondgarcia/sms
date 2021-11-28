package com.demo.sms.student;

import com.demo.sms.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Page<Student> getStudents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Student findByEmail(String email) throws RuntimeException {
        return repository.findStudentByEmail(email).orElseThrow();
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException("Student not found with ID: " + id);
        }
    }

    @Transactional
    public Student updateStudent(Long id, Student student) {
        Student originalStudent = getStudent(id);
        originalStudent.setName(student.name);
        originalStudent.setEmail(student.email);
        return repository.save(originalStudent);
    }

    public Student getStudent(Long id) {
        return repository.findById(id).orElseThrow(() -> new ApiRequestException("Student not found with ID: " + id));
    }
}
