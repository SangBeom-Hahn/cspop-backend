package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.WrongPhoneNumberPatternException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PhoneNumberTest {

    @ParameterizedTest
    @DisplayName("올바르지 않은 전화번호 패턴으로 예외가 발생한다.")
    @ValueSource(strings = {"0-0-0", "010-23333333", "0102313-3123", "0101313122"})
    void throwException_invalidPattern(String phoneNumber) {
        assertThatThrownBy(() -> PhoneNumber.from(phoneNumber))
                .isInstanceOf(WrongPhoneNumberPatternException.class)
                .hasMessage(String.format("올바르지 않은 패턴입니다. 번호 = {%s}", phoneNumber));
    }

    @ParameterizedTest
    @DisplayName("PhoneNumber 컴포넌트를 생성한다.")
    @ValueSource(strings = {"010-1111-1111", "010-1234-1234"})
    void construct(String phoneNumber) {
        assertDoesNotThrow(() -> PhoneNumber.from(phoneNumber));
    }
}