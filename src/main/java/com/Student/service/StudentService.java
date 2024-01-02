package com.Student.service;

import com.Student.entity.Student;
import com.Student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepository;
    public Student getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return student;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
    return studentRepository.findAll();
    }
}
