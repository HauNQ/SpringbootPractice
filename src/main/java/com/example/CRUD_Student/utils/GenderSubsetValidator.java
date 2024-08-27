package com.example.CRUD_Student.utils;

import com.example.CRUD_Student.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Objects;

public class GenderSubsetValidator implements ConstraintValidator<GenderSubset, Object> {
    private  Object[] genders;

    @Override
    public void initialize(GenderSubset genderSubset) {
        this.genders = genderSubset.anyOf();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(genders).contains(value);
    }
}
