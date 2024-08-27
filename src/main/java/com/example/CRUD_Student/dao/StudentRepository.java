package com.example.CRUD_Student.dao;

import com.example.CRUD_Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {
    public Student findByFirstName(String firstName);

    public Student findByFirstNameAndLastName(String firstName, String lastName);

//    public List<Student> findByFirstNameContaining(String firstName);

    @Query("SELECT s from Student s WHERE s.firstName like %?1%")
    public List<Student> findByFirstNameLike(String firstName);

}
