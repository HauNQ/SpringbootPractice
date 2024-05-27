package com.example.RestfulAPI;

import com.example.RestfulAPI.entity.ErrorResponse;
import com.example.RestfulAPI.entity.Student;
import com.example.RestfulAPI.exception.StudentException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<Student> students;

    @PostConstruct
    public void initFakeStudents() {
        System.out.println("Init FakeStudents");
        students = new ArrayList<>();
        students.add(new Student(1, "Nguyen Quang Hau", "Software Engineer", 9.2, 18));
        students.add(new Student(2, "Nguyen Thanh Tai", "Designer", 8.5, 20));
        students.add(new Student(3, "Le Phan Nhat Minh", "Investor", 7.9, 18));
    }


    @GetMapping("/list")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student st = (Student) students.stream().toList().stream().filter(student -> {
            return student.getId() == id;
        }).limit(1).findFirst().orElse(null);

        if (st == null) {
            throw new StudentException("Id is invalid");
        }

        return st;
    }

    @GetMapping("/index/{index}")
    public Student getStudentByIndex(@PathVariable int index)  {
        Student st = null;
        try {
            st = students.get(index);
            return st;
        }catch (IndexOutOfBoundsException ex){
            throw new StudentException("Index is invalid");
        }
    }









}
