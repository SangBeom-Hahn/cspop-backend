package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.*;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.NoSuchMiddleException;
import com.kyonggi.cspop.exception.NoSuchProposalException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.service.ServiceTest;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MiddleServiceTest extends ServiceTest {

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
    @DisplayName("회원가입하지 않은 학생이 중간 보고서를 제출하면 예외가 발생한다.")
    void throwException_noSuchStudent() {
        // given
        MiddleSaveRequestDto middleSaveRequestDto = createMiddleSaveRequestDto();
        Long invalidId = 999L;

        // then
        assertThatThrownBy(() -> middleService.saveMiddle(middleSaveRequestDto, invalidId))
                .isInstanceOf(NoSuchStudentIdException.class)
                .hasMessage(String.format("가입되지 않은 학생입니다. studentId={%d}", invalidId));
    }

    @Test
    @DisplayName("중간 보고서를 신청하지 않은 학생을 승인하면 예외가 발생한다.")
    void throwException_NoSuchMiddle() {
        assertThatThrownBy(() -> middleService.approveMiddle(student.getId()))
                .isInstanceOf(NoSuchMiddleException.class)
                .hasMessage(String.format("중간보고서 제출을 하지 않은 학생입니다. id = {%d}", student.getId()));
    }

    @Test
    @DisplayName("중간 보고서를 승인 상태로 수정한다.")
    void updateApprove() {
        // given
        MiddleSaveRequestDto middleSaveRequestDto = createMiddleSaveRequestDto();
        Long saveId = middleService.saveMiddle(middleSaveRequestDto, student.getId())
                .getId();

        // when
        middleService.approveMiddle(student.getId());
        Middle middle = middleRepository.findById(saveId)
                .orElseThrow();

        // then
        assertAll(
                () -> assertThat(graduation.getStep()).isEqualTo(Step.FINAL),
                () -> assertThat(middle.getApprove()).isEqualTo(true)
        );
    }

    @Test
    @DisplayName("중간보고서의 승인을 거절한다.")
    void rejectMiddle() {
        // given
        MiddleSaveRequestDto middleSaveRequestDto = createMiddleSaveRequestDto();
        Long saveId = middleService.saveMiddle(middleSaveRequestDto, student.getId())
                .getId();
        String reason = "reason";

        // when
        middleService.rejectMiddle(student.getId(), reason);
        Middle middle = middleRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(middle).extracting("approve", "reason")
                .containsExactly(false, reason);
    }

    private MiddleSaveRequestDto createMiddleSaveRequestDto() {
        return new MiddleSaveRequestDto(
                "title",
                Type.from("구현"),
                "text",
                "plan"
        );
    }
}