package com.kyonggi.cspop.domain.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("태어난 날짜가 현재보다 크면 예외가 발생한다.")
    void throwException_invalidBirth() {
        //given
        LocalDate invalidBirth = now().plusDays(1);

        //then
        Assertions.assertThatThrownBy(() -> new Student(
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
        ));
    }

    @Test
    @DisplayName("학생을 생성한다.")
    void construct() {
        //given
        LocalDate validBirth = now().minusDays(1);

        //then
        Assertions.assertThatThrownBy(() -> new Student(
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