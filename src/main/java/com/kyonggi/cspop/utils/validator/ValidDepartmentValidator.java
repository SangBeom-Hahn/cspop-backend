package com.kyonggi.cspop.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidDepartmentValidator implements ConstraintValidator<ValidDepartment, String> {
    private static final List<String> ALLOWED_DEPARTMENTS = List.of("인공지능", "컴퓨터공학", "융합보안");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ALLOWED_DEPARTMENTS.contains(value);
    }
}
