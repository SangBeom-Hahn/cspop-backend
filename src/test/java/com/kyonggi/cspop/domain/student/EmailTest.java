package com.kyonggi.cspop.domain.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @ParameterizedTest
    @DisplayName("올바르지 않은 패턴의 이메일은 예외가 발생한다.")
    @ValueSource(strings = {"q", "1231", "32r21@na", "2342naver.com"})
    void throwException_inValidPattern(String email) {
        assertThatThrownBy(() -> Email.from(email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("Email 컴포넌트를 외부에서 팩토리 메서드로 생성한다.")
    @ValueSource(strings = {"1@naver.com", "a@gmail.com", "*@naver.com"})
    void construct (String email) {
        assertDoesNotThrow(() -> Email.from(email));
    }
}