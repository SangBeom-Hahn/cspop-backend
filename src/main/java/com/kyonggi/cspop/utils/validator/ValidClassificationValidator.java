package com.kyonggi.cspop.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidClassificationValidator implements ConstraintValidator<ValidClassification, String> {

    private static final List<String> ALLOWED_CLASSIFICATIONS = List.of("학부생", "복수전공", "교수", "대학원생");

    @Override
    public boolean isValid(final String value, ConstraintValidatorContext context) {
        return ALLOWED_CLASSIFICATIONS.contains(value);
    }
}
