package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchGradeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    @DisplayName("[1, 2, 3, 4학년]이 아니면 예외가 발생한다.")
    void throwException_invalidValue() {
        // given
        String invalidValue = "5학년";

        // then
        assertThatThrownBy(() -> Grade.from(invalidValue))
                .isInstanceOf(NoSuchGradeException.class)
                .hasMessage(String.format("존재 하지 않는 학년입니다. 학년 = {%s}", invalidValue));
    }

    @ParameterizedTest
    @DisplayName("학년 분류를 생성한다.")
    @ValueSource(strings = {"1학년", "2학년", "3학년", "4학년"})
    void construct(String value) {
        // then
        assertDoesNotThrow(() -> Grade.from(value));
    }
}