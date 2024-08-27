package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Department;
import com.example.RelationalMappingPractice.entity.Teacher;

import java.util.List;

public interface DepartmentDAO {
    Department getDepartmentById(int id);
    Department updateDepartment(Department department);
}
