package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dto.request.StudentRequest;
import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.entity.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public Student getStudentByFirstName(String firstName);
    public Student getStudentByFirstNameAndLastName(String firstName, String lastName);
    public List<Student> findByFirstNameContaining(String firstName);
    public PageResponse<?> getStudentWithPage(int pageNo, int pageNum, String... sorts);
    public List<Student> getListStudentTest();
    public Long addStudent(StudentRequest studentRequest);
    public PageResponse<?> getsearchedStudentWithPage(int pageNo, int pageNum, String search ,String sorts);
    public PageResponse<?> searchByCriteria(Pageable pageable,String sorts,String[] address,String... search);
    public PageResponse<?> searchBySpecification(Pageable pageable, String search , String sort,String city);

}
