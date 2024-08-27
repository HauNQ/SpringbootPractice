package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Teacher;
import com.example.RelationalMappingPractice.entity.TeacherDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDetailDaoImpli implements TeacherDetailDAO {

    private EntityManager entityManager;

    @Autowired
    public TeacherDetailDaoImpli(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(TeacherDetail teacherDetail) {
        entityManager.persist(teacherDetail);
    }

    @Override
    public TeacherDetail  findTeacherDetailById(int id) {
        return entityManager.find(TeacherDetail.class,id);
    }
}
