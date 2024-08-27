package com.kyonggi.cspop.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidGradeValidator implements ConstraintValidator<ValidGrade, String> {
    private static final List<String> ALLOWED_GRADES = List.of("1학년", "2학년", "3학년", "4학년");

    @Override
    public boolean isValid(final String value, ConstraintValidatorContext context) {
        return ALLOWED_GRADES.contains(value);
    }
}
