package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.WrongEmailPatternException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailTest {

    @ParameterizedTest
    @DisplayName("올바르지 않은 패턴의 이메일은 예외가 발생한다.")
    @ValueSource(strings = {"q", "1231", "32r21@na", "2342naver.com"})
    void throwException_inValidPattern(String email) {
        assertThatThrownBy(() -> Email.from(email))
                .isInstanceOf(WrongEmailPatternException.class)
                .hasMessage(String.format("올바르지 않은 이메일 패턴입니다. 이메일 = {%s}", email));
    }

    @ParameterizedTest
    @DisplayName("Email 컴포넌트를 외부에서 팩토리 메서드로 생성한다.")
    @ValueSource(strings = {"1@naver.com", "a@gmail.com", "1a@naver.com"})
    void construct (String email) {
        assertDoesNotThrow(() -> Email.from(email));
    }
}