package com.kyonggi.cspop.domain.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ClassificationTest {
    
    @Test
    @DisplayName("[학부생, 대학원생, 교수, 복수전공]가 아니면 예외가 발생한다.")
    void throwException_invalidValue() {
        // given
        String invalidValue = "조교";

        // then
        assertThatThrownBy(() -> Classification.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("학생 분류를 생성한다.")
    @ValueSource(strings = {"학부생", "대학원생", "교수", "복수전공"})
    void construct(String value) {
        // then
        assertDoesNotThrow(() -> Classification.from(value));
    }
}