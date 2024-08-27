package com.example.CRUD_Student.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumPatternValidator.class )
public @interface EnumPattern {
    String name();
    String regexp();
    String message() default "{name} must be matched {regexp}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
