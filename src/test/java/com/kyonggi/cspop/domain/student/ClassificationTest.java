package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchClassificationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ClassificationTest {
    
    @Test
    @DisplayName("[학부생, 대학원생, 교수, 복수전공]가 아니면 예외가 발생한다.")
    void throwException_invalidValue() {
        // given
        String invalidValue = "조교";

        // then
        assertThatThrownBy(() -> Classification.from(invalidValue))
                .isInstanceOf(NoSuchClassificationException.class)
                .hasMessage(String.format("존재하지 않은 학년 분류입니다. 분류 = {%s}", invalidValue));
    }

    @ParameterizedTest
    @DisplayName("학생 분류를 생성한다.")
    @ValueSource(strings = {"학부생", "대학원생", "교수", "복수전공"})
    void construct(String value) {
        // then
        assertDoesNotThrow(() -> Classification.from(value));
    }
}