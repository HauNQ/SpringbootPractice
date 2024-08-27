package com.example.CRUD_Student.service;

import com.example.CRUD_Student.entity.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentByFirstName(String firstName);
    public Student getStudentByFirstNameAndLastName(String firstName, String lastName);
    public List<Student> findByFirstNameContaining(String firstName);

}
