package com.kyonggi.cspop.utils.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ValidGradeValidatorTest {
    private ValidGradeValidator validator;

    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new ValidGradeValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    @DisplayName("허용하지 않는 학년이 들어오면 validator를 통과하지 못한다")
    void notAllowedGrade() {
        // given
        final String unknown = "unknown";

        // when
        boolean actual = validator.isValid(unknown, context);

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1학년", "2학년", "3학년", "4학년"})
    @DisplayName("허용된 학년이 입력되면 검증을 통과한다.")
    void validGrade(String password) {
        assertThat(validator.isValid(password, context))
                .isTrue();
    }
}