package com.example.CRUD_Student.rest;

import com.example.CRUD_Student.dto.request.StudentRequest;
import com.example.CRUD_Student.dto.response.ResponseData;
import com.example.CRUD_Student.dto.response.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/student")
@Slf4j
@Validated
public class TestAPI {

    @Operation(summary = "Test Add User", description = "Test Add User Description")
    @PostMapping("/")
    public ResponseData<?> addStudent(@Valid @RequestBody StudentRequest student){
      log.info("Add student");
      try {
          log.info(student.toString());
          return new ResponseData<>(HttpStatus.CREATED.value(),"Added Student", 1);
      }catch (Exception e){
          log.error(e.getMessage());
          return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Added Student Unsuccessfully");
      }
    }
}
