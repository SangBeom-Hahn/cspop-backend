package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Method;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraduationRepositoryTest extends RepositoryTest {

    Student student;
    Graduation graduation;

    @BeforeEach
    void setUp() {
        student = new Student(
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
        graduation = new Graduation(
                Method.THESIS,
                Status.UN_APPROVAL,
                Step.RECEIVE,
                false,
                now().plusDays(1),
                "professor",
                student
        );
        studentRepository.save(student);
    }

    @Test
    @DisplayName("졸업을 신청한 학생을 찾는다.")
    void findByStudent() {
        // given
        graduationRepository.save(graduation);

        // when
        Graduation findGraduation = graduationRepository.findByStudent(student)
                .orElseThrow();

        // then
        assertAll(
                () -> assertThat(findGraduation.getId()).isNotNull(),
                () -> assertThat(findGraduation).isEqualTo(graduation)
        );
    }
}