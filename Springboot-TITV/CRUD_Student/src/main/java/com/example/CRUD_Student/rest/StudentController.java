package com.example.CRUD_Student.rest;

import com.example.CRUD_Student.entity.Student;
import com.example.CRUD_Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private  StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getStudentByFirstName(@PathVariable String firstName) {
        Student student = studentService.getStudentByFirstName(firstName);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(student);
        }
    }

    @GetMapping("/containing/{firstName}")
    public ResponseEntity<List<Student>> findByFirstNameContaining(@PathVariable String firstName) {
        List<Student> students = studentService.findByFirstNameContaining(firstName);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(students);
        }
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Student> getStudentByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        Student student = studentService.getStudentByFirstNameAndLastName(firstName, lastName);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(student);
        }
    }


}
