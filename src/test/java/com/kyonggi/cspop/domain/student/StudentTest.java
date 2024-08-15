package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NotReachedBirthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StudentTest {

    @Test
    @DisplayName("태어난 날짜가 현재보다 크면 예외가 발생한다.")
    void throwException_invalidBirth() {
        //given
        LocalDate invalidBirth = now().plusDays(1);

        //then
        assertThatThrownBy(() -> new Student(
                "201811111",
                "123",
                "111&!a",
                invalidBirth,
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        ))
                .isInstanceOf(NotReachedBirthException.class)
                .hasMessage(String.format("생년월일은 현재 시점보다 늦을 수 없습니다. 생년월일 = {%s}", invalidBirth));
    }

    @Test
    @DisplayName("학생을 생성한다.")
    void construct() {
        //given
        LocalDate validBirth = now().minusDays(1);

        //then
        assertDoesNotThrow(() -> new Student(
                "201811111",
                "123",
                "111&!a",
                validBirth,
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        ));
    }
}