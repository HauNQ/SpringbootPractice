package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.TeacherDetail;

public interface TeacherDetailDAO {
    void save(TeacherDetail teacherDetail);
    TeacherDetail findTeacherDetailById(int id);
}
