package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.NotReachedGraduationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class GraduationTest {

    @Test
    @DisplayName("졸업 날짜가 현재보다 이전이면 예외가 발생한다.")
    void throwException_invalidDate() {
        // given
        Student student = new Student(
                1L,
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT,
                RoleType.ADMIN
        );

        // then
        assertThatThrownBy(() -> new Graduation(
                        Method.THESIS,
                        Status.UN_APPROVAL,
                        Step.RECEIVE,
                        false,
                        now().minusDays(1),
                        "professorName",
                        student
                )).isInstanceOf(NotReachedGraduationException.class)
                .hasMessage(String.format("졸업 날짜는 현재 시점보다 빠를 수 없습니다. 졸업날짜 = {%s}", now().minusDays(1)));
    }

    @Test
    @DisplayName("졸업자의 승인 상태를 수정한다.")
    void changeStatus() {
        // given
        Student student = new Student(
                1L,
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT,
                RoleType.ADMIN
        );
        Graduation graduation = new Graduation(
                Method.THESIS,
                Status.UN_APPROVAL,
                Step.RECEIVE,
                false,
                now().plusDays(1),
                "professorName",
                student
        );
        Status changeStatus = Status.REJECT;

        // when
        graduation.changeStatus(changeStatus);

        // then
        assertThat(graduation.getStatus())
                .isEqualTo(changeStatus);
    }
    
    @Test
    @DisplayName("졸업자 조회를 생성한다.")
    void construct() {
        // given
        Student student = new Student(
                1L,
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT,
                RoleType.ADMIN
        );

        assertDoesNotThrow(() -> new Graduation(
                Method.THESIS,
                Status.UN_APPROVAL,
                Step.RECEIVE,
                false,
                now().plusDays(1),
                "professorName",
                student
        ));
    }
}