package com.kyonggi.cspop.domain.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @ParameterizedTest
    @DisplayName("올바르지 않은 전화번호 패턴으로 예외가 발생한다.")
    @ValueSource(strings = {"0-0-0", "010-23333333", "0102313-3123", "0101313122"})
    void throwException_invalidPattern(String phoneNumber) {
        assertThatThrownBy(() -> PhoneNumber.from(phoneNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("PhoneNumber 컴포넌트를 생성한다.")
    @ValueSource(strings = {"010-1111-1111", "012-1111-1111"})
    void construct(String phoneNumber) {
        assertDoesNotThrow(() -> PhoneNumber.from(phoneNumber));
    }
}