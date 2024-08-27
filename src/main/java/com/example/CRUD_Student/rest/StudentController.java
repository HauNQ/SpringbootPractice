package com.example.CRUD_Student.rest;

import com.example.CRUD_Student.dto.request.AddressRequest;
import com.example.CRUD_Student.dto.request.StudentRequest;
import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.dto.response.ResponseData;
import com.example.CRUD_Student.dto.response.ResponseError;
import com.example.CRUD_Student.entity.Student;
import com.example.CRUD_Student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Slf4j
@Tag(name = "Student API")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getStudentByFirstName(@PathVariable String firstName) {
        Student student = studentService.getStudentByFirstName(firstName);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }

    @GetMapping("/containing/{firstName}")
    public ResponseEntity<List<Student>> findByFirstNameContaining(@PathVariable String firstName) {
        List<Student> students = studentService.findByFirstNameContaining(firstName);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Student> getStudentByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        Student student = studentService.getStudentByFirstNameAndLastName(firstName, lastName);
        if (student == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }


    @GetMapping("/list")
    public ResponseData<?> getStudentWithPage(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(required = false) String... sorts) {
        try {
            return new ResponseData<PageResponse<?>>(HttpStatus.OK.value(), "Get list of student successfully", studentService.getStudentWithPage(pageNo, pageSize, sorts));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/list/search")
    public ResponseData<PageResponse<?>> getList(@RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "4", required = false) int pageSize
            , @RequestParam(required = false) String search, @RequestParam(required = false) String sort) {
        try {
            PageResponse<?> pageResponse = this.studentService.getsearchedStudentWithPage(pageNo, pageSize, search, sort);
            return new ResponseData<>(HttpStatus.OK.value(), "Get list searched student sucessfully", pageResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get list searched student unsucessfully");
        }
    }

    @PostMapping("/")
    public ResponseData<?> addStudent(@Valid @RequestBody StudentRequest student) {
        log.info(student.toString());
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "Student added successfully", studentService.addStudent(student));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @Operation(summary = "Search By Criteria", description = "Search by criteria description")
    @GetMapping("/list/search-by-criteria")
    public ResponseData<PageResponse<?>> searchByCriteria(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String[] address,
            @RequestParam(required = false) String... search) {
        try {
           /* Arrays.asList(search).forEach(System.out::println);*/
            return new ResponseData<>(HttpStatus.OK.value(), "Get list searched student successfully",studentService.searchByCriteria(pageable,sortBy,address,search) );
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get list searched student unsucessfully");
        }
    }


    @GetMapping("/list/search-by-specification")
    public ResponseData<PageResponse<?>> searchBySpecification(Pageable pageable, @RequestParam(required = false) String search, @RequestParam(required = false) String sort, @RequestParam(required = false) String city) {
        try {
            /* PageResponse<?> pageResponse =  this.studentService.searchByCriteria(pageNo,pageSize,search,sort);*/
            return new ResponseData<>(HttpStatus.OK.value(), "Get list searched student sucessfully", this.studentService.searchBySpecification(pageable, search, sort, city));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
