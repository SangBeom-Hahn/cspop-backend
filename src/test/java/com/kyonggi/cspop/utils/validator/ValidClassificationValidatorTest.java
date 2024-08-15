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

class ValidClassificationValidatorTest {

    private ValidClassificationValidator validator;

    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new ValidClassificationValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    @DisplayName("허용하지 않는 학생 구분이 들어오면 validator를 통과하지 못한다")
    void notAllowedClassification() {
        // given
        final String unknown = "unknown";

        // when
        boolean actual = validator.isValid(unknown, context);

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"학부생", "대학원생", "교수", "복수전공"})
    @DisplayName("허용된 학생 구분가 입력되면 검증을 통과한다.")
    void validClassification(String password) {
        assertThat(validator.isValid(password, context))
                .isTrue();
    }
}