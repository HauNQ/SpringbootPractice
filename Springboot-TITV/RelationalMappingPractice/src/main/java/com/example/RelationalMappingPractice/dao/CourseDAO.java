package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Course;

public interface CourseDAO {
    Course findCourseById(int id);
    Course findCourseByIdJoinFetch(int id);
}
