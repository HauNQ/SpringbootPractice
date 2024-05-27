package com.example.RelationalMappingPractice.dao;

import com.example.RelationalMappingPractice.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImpli implements DepartmentDAO{

    private EntityManager entityManager;

    @Autowired
    public DepartmentDAOImpli(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Department getDepartmentById(int id) {
        String query = "select d from Department d where d.id = :id";
        return (Department) entityManager.createQuery(query).setParameter("id", id).getResultList().get(0);
    }

    @Override
    @Transactional
    public Department updateDepartment(Department department) {
        Department d = getDepartmentById(department.getId());
        d.setDescription(department.getDescription());
        d.setName(department.getName());
        d.setTeacherList(department.getTeacherList());
        entityManager.merge(d);
        return d;
    }
}
