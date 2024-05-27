package com.example.RelationalMappingPractice.dao;


import com.example.RelationalMappingPractice.entity.Teacher;

public interface TeacherDAO {
    void save(Teacher teacher);
    Teacher findTeacherById(int id);
    void deleteTeacherById(int id);
}
