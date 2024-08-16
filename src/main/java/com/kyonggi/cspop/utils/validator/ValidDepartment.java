package com.kyonggi.cspop.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDepartmentValidator.class)
public @interface ValidDepartment {

    String message() default "학과는 인공지능, 컴퓨터공학, 융합 보안만 가능합니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
