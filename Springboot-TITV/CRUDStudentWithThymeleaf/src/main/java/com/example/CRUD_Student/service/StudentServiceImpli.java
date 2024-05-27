package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dao.StudentRepository;
import com.example.CRUD_Student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpli implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpli(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    @Override
    public boolean save(Student student) {
        return this.studentRepository.save(student) != null;
    }

    @Override
    public Student update(Student student) {
        Student st = this.studentRepository.save(student);
        this.studentRepository.flush();
        return st;
    }

    @Override
    public void delete(int id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Student findById(int id) {
        Optional<Student> st = this.studentRepository.findById(id);
        return st.orElse(null);
    }
}
