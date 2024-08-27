package com.example.CRUD_Student.dto.request;

import com.example.CRUD_Student.enums.Gender;
import com.example.CRUD_Student.enums.StudentStatus;
import com.example.CRUD_Student.utils.EnumPattern;
import com.example.CRUD_Student.utils.EnumValue;
import com.example.CRUD_Student.utils.GenderSubset;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Arrays;

@Builder
@Getter
@ToString(includeFieldNames = false)
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotNull(message = "Id must be not null")
    private Long id;
    private String email;

    @NotBlank
    private String firstName;
    private String lastName;
    private AddressRequest addressRequest;

    @GenderSubset(name = "gender", anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER})
    private Gender gender;

    @EnumPattern(name = "status", regexp = "^ACTIVE|INACTIVE|NONE$")
    private StudentStatus status;
    
    @EnumValue(name = "gender", message = "ad", enumClass = Gender.class)
    private String type;

   /* public StudentRequest(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }*/
}
