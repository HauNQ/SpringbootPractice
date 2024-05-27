package com.example.connectToDatabase.DAO;

import com.example.connectToDatabase.entity.StudentEntity;

import java.util.List;

public interface IStudentDAO {
    void save(StudentEntity student);
    StudentEntity findById(int id);
    List<StudentEntity> findAll();
    List<StudentEntity> findByName(String fName);
    void update(StudentEntity student);
    void updateJpql(StudentEntity student);
    void delete(StudentEntity student);
    void deleteByFirstName(String fName);
}
