package com.example.connectToDatabase.DAO;

import com.example.connectToDatabase.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StudentDAO implements IStudentDAO{

    @Override
    public void update(StudentEntity student) {
        this.entityManager.merge(student);
    }

    private EntityManager  entityManager;

    @Autowired
    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void save(StudentEntity student) {
        this.entityManager.persist(student);
    }

    @Override
    public StudentEntity findById(int id) {
        return this.entityManager.find(StudentEntity.class, id);
    }

    @Override
    public List<StudentEntity> findAll() {
       String jpql = "Select s from StudentEntity s";
       return this.entityManager.createQuery(jpql, StudentEntity.class).getResultList();
    }

    @Override
    public List<StudentEntity> findByName(String fName) {
        String jpql = "Select s from StudentEntity s where s.firstName = :fname";
        return this.entityManager.createQuery(jpql, StudentEntity.class).setParameter("fname", fName).getResultList();
    }

    @Override
    public void updateJpql(StudentEntity  student) {
//        String jpql = "Update StudentEntity s set s.firstName = 'HauUpdate' where s.id = 6";
//        Query query = this.entityManager.createQuery(jpql, StudentEntity.class);
//        query.executeUpdate();
        String jpql = "UPDATE StudentEntity s SET s.firstName = 'HauUpdate2' WHERE s.id = 6";
        Query query = this.entityManager.createQuery(jpql);
        query.executeUpdate();
    }

    @Override
    public void delete(StudentEntity student) {
//        StudentEntity student = this.entityManager.find(StudentEntity.class, id);
        if(!this.entityManager.contains(student)) {
            student = this.entityManager.merge(student);
            System.out.println(student.toString());
        }
        this.entityManager.remove(student);
    }

    @Override
    public void deleteByFirstName(String fName) {
        String jpql = "DELETE FROM StudentEntity s  WHERE s.firstName = :fName";
        Query query = this.entityManager.createQuery(jpql);
        query.setParameter("fName", fName);
        query.executeUpdate();
    }


}
