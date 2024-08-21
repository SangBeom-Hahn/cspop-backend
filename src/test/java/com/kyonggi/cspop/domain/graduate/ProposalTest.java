package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProposalTest {

    @Test
    @DisplayName("제안서 제출을 생성한다.")
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

        // then
        Assertions.assertDoesNotThrow(() -> new Proposal("title", Type.from("구현"), "qualification", "content", student));
    }

    @Test
    @DisplayName("반려 사유를 수정한다.")
    void changeReason() {
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
        Proposal proposal = new Proposal("title", Type.from("구현"), "qualification", "content", student);
        String changeReason = "reason";

        // when
        proposal.changeReason(changeReason);

        // then
        assertThat(proposal.getReason())
                .isEqualTo(changeReason);
    }
}