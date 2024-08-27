package com.example.CRUD_Student.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private List<String> genders;
    @Override
    public void initialize(EnumValue enumValue) {
            genders = Stream.of(enumValue.enumClass().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        return gender == null || genders.contains(gender.toUpperCase());
    }

}
