package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Method;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.exception.NoSuchSubmitException;
import com.kyonggi.cspop.service.ServiceTest;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.cspop.domain.schedule.Step.PROPOSAL;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SubmitServiceTest extends ServiceTest {

    Student student;
    Graduation graduation;

    @BeforeEach
    void setUp() {
        student = new Student(
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
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
        graduationRepository.save(graduation);
    }

    @Test
    @DisplayName("회원가입을 하지 않은 학생이 졸업을 신청하면 예외가 발생한다.")
    void throwException_noSuchStudent() {
        assertThatThrownBy(() -> submitService.saveSubmit(999L))
                .isInstanceOf(NoSuchStudentIdException.class)
                .hasMessage(String.format("가입되지 않은 학생입니다. studentId={%d}", 999L));
    }

    @Test
    @DisplayName("졸업 신청 접수를 하지 않은 학생을 승인하면 예외가 발생한다.")
    void throwException_noSuchSubmit() {
        // given
        SubmitUpdateRequestDto submitUpdateRequestDto = new SubmitUpdateRequestDto(
                now().plusDays(1),
                false,
                false,
                "reason"
        );

        // then
        assertThatThrownBy(() -> submitService.approveSubmit(submitUpdateRequestDto, student.getId()))
                .isInstanceOf(NoSuchSubmitException.class)
                .hasMessage(String.format("접수를 하지 않은 학생입니다. id = {%d}", student.getId()));
    }

    @Test
    @DisplayName("신청을 접수한다.")
    void saveSubmitAndFind() {
        // given
        Long saveId = submitService.saveSubmit(student.getId())
                .getId();

        // when
        Submit submit = submitRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(submit).extracting("id", "name")
                .containsExactly(saveId, student.getName());
    }

    @Test
    @DisplayName("신청한 접수를 승인한다.")
    void approveSubmit() {
        // given
        Long saveId = submitService.saveSubmit(student.getId())
                .getId();

        // when
        SubmitUpdateRequestDto submitUpdateRequestDto = new SubmitUpdateRequestDto(
                now().plusDays(1),
                false,
                false,
                "reason"
        );
        submitService.approveSubmit(submitUpdateRequestDto, student.getId());
        Submit submit = submitRepository.findById(saveId)
                .orElseThrow();

        // then
        assertAll(
                () -> assertThat(student.getGraduation()).extracting("step", "date", "professorName")
                        .containsExactly(PROPOSAL, submitUpdateRequestDto.getGraduateDate(), student.getName()),
                () -> assertThat(submit).extracting("graduateDate", "completion", "approve", "reason")
                        .containsExactly(now().plusDays(1), false, false, "reason")
        );
    }
}