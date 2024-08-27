package com.example.CRUD_Student.utils;

import com.example.CRUD_Student.enums.Gender;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = GenderSubsetValidator.class)
public @interface GenderSubset {
    String name();
    Gender[] anyOf();
    String message() default "must be any {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
