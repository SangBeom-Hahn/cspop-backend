package com.kyonggi.cspop.controller.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;

import java.util.Objects;
import java.util.Set;

public class RequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected Set<ConstraintViolation<Object>> getConstraintViolation(Object object) {
        return validator.validate(object);
    }
}
