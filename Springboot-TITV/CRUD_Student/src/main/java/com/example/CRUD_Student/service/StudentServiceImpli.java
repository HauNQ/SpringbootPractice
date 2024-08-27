package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dao.StudentRepository;
import com.example.CRUD_Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpli implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpli(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public Student getStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Student> findByFirstNameContaining(String firstName) {
        return this.studentRepository.findByFirstNameLike(firstName);
    }
}
