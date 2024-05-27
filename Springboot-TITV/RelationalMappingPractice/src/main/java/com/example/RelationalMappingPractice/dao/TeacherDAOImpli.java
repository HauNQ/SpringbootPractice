package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOImpli implements TeacherDAO{

    private EntityManager  entityManager;

    @Autowired
    public TeacherDAOImpli(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public Teacher findTeacherById(int id) {
       return entityManager.find(Teacher.class, id);
    }

    @Override
    @Transactional
    public void deleteTeacherById(int id) {
        Teacher teacher = findTeacherById(id);
        entityManager.remove(teacher);
        System.out.println("delete teacher"+teacher);
    }
}
