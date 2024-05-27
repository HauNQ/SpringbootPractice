package com.example.CRUD_Student.service;

import com.example.CRUD_Student.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudent();
    public boolean save(Student student);
    public Student update(Student student);
    public void delete(int id);
    public Student findById(int id);
}
