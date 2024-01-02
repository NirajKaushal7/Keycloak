package com.Student.controller;

import com.Student.entity.Student;
import com.Student.service.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:8080")
@SecurityRequirement(name = "Keycloak")//by this access token is added in header of all api
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('user')")//when we use Springboot use this
    //when we use keycloak we don't use any annotations
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        logger.info("Student id : {}",id);
        Student student = studentService.getStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
    @GetMapping()
//    @PreAuthorize("hasRole('admin')")//when we use Springboot use this
//    //when we use keycloak we don't use any annotations
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PostMapping("/add")//public api
    public ResponseEntity<Student> addBook(@RequestBody Student student){
        Student addedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }
    @PostMapping("/scope")
    public String post() {
        return "Post Mapping";
    }
    @PutMapping("/scope")
    public String update() {
        return "Put Mapping";
    }
    }
