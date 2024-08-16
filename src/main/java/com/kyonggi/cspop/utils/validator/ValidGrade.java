package com.kyonggi.cspop.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidGradeValidator.class)
public @interface ValidGrade {

    String message() default "[1, 2, 3, 4학년만 가능합니다.]";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
