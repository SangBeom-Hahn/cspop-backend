package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchDepartmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DepartmentTest {

    @Test
    @DisplayName("[인공지능, 컴퓨터공학, 융합보안]가 아니면 예외가 발생한다.")
    void throwException_invalidValue() {
        // given
        String invalidValue = "전기전자";

        // then
        assertThatThrownBy(() -> Department.from(invalidValue))
                .isInstanceOf(NoSuchDepartmentException.class)
                .hasMessage(String.format("존재 하지 않는 학과입니다. 학과 = {%s}", invalidValue));
    }

    @ParameterizedTest
    @DisplayName("학과 분류를 생성한다.")
    @ValueSource(strings = {"인공지능", "컴퓨터공학", "융합보안"})
    void construct(String value) {
        // then
        assertDoesNotThrow(() -> Department.from(value));
    }
}