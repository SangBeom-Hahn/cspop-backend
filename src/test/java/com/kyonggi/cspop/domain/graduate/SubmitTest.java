package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubmitTest {

    @Test
    @DisplayName("신청 접수를 생성한다.")
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
        assertDoesNotThrow(() -> new Submit("name", student));
    }

    @Test
    @DisplayName("신청 접수 승인 여부를 수정한다.")
    void changeApprove() {
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
        Submit submit = new Submit("name", student);
        Boolean changeApprove = true;

        // when
        submit.changeApprove(changeApprove);

        // then
        assertThat(submit.getApprove())
                .isEqualTo(changeApprove);
    }
}