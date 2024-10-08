package com.kyonggi.cspop.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidClassificationValidator.class)
public @interface ValidClassification {

    String message() default "신분은 [학부생, 복수전공, 교수, 대학원생] 만 가능합니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
