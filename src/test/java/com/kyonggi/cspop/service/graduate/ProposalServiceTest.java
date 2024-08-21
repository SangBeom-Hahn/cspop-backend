package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.*;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.NoSuchProposalException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.service.ServiceTest;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProposalServiceTest extends ServiceTest {

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
    @DisplayName("회원가입하지 않은 학생이 제안서를 제출하면 예외가 발생한다.")
    void throwException_noSuchStudent() {
        // given
        ProposalSaveRequestDto proposalSaveRequestDto = createProposalSaveRequestDto();
        Long invalidId = 999L;

        // then
        assertThatThrownBy(() -> proposalService.saveProposal(proposalSaveRequestDto, invalidId))
                .isInstanceOf(NoSuchStudentIdException.class)
                .hasMessage(String.format("가입되지 않은 학생입니다. studentId={%d}", invalidId));
    }

    @Test
    @DisplayName("제안서를 신청하지 않은 학생을 승인하면 예외가 발생한다.")
    void throwException_NoSuchProposal() {
        assertThatThrownBy(() -> proposalService.approveProposal(student.getId()))
                .isInstanceOf(NoSuchProposalException.class)
                .hasMessage(String.format("제안서 제출을 하지 않은 학생입니다. id = {%d}", student.getId()));
    }

    @Test
    @DisplayName("제안서를 승인 상태로 수정한다.")
    void updateApprove() {
        // given
        ProposalSaveRequestDto proposalSaveRequestDto = createProposalSaveRequestDto();
        Long saveId = proposalService.saveProposal(proposalSaveRequestDto, student.getId())
                .getId();

        // when
        proposalService.approveProposal(student.getId());
        Proposal proposal = proposalRepository.findById(saveId)
                .orElseThrow();

        // then
        assertAll(
                () -> assertThat(graduation.getStep()).isEqualTo(Step.MIDDLE),
                () -> assertThat(proposal.getApprove()).isEqualTo(true)
        );
    }

    @Test
    @DisplayName("제안서의 승인을 거절한다.")
    void rejectProposal() {
        // given
        ProposalSaveRequestDto proposalSaveRequestDto = createProposalSaveRequestDto();
        Long saveId = proposalService.saveProposal(proposalSaveRequestDto, student.getId())
                .getId();
        String reason = "reason";

        // when
        proposalService.rejectProposal(student.getId(), reason);
        Proposal proposal = proposalRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(proposal).extracting("approve", "reason")
                .containsExactly(false, reason);
    }

    private ProposalSaveRequestDto createProposalSaveRequestDto() {
        return new ProposalSaveRequestDto(
                "title",
                Type.from("구현"),
                "qualification",
                "content"
        );
    }
}