package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAOImpli implements CourseDAO{

    private EntityManager entityManager;

    @Autowired
    public CourseDAOImpli(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course findCourseByIdJoinFetch(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "Join fetch c.teacher " +
                "where c.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
