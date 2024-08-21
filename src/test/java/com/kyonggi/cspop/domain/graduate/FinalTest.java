package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class FinalTest {

    @Test
    @DisplayName("최종 보고서를 생성한다.")
    void consturct() {
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
        assertDoesNotThrow(() -> new Final("title", Type.from("구현"), "qualification", 1, student));
    }
}