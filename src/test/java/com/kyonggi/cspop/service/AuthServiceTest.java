package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.student.StudentLoginRequest;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.IdPasswordMismatchException;
import com.kyonggi.cspop.exception.NoSuchStudentException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.service.dto.student.TokenResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthServiceTest extends ServiceTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(
                "201811111",
                "125",
                passwordEncoder.encode("123$a"),
                LocalDate.of(1999, 9, 17),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        );
        studentRepository.save(student);
    }

    @Test
    @DisplayName("회원가입되지 않은 학생이 로그인을 시도하면 예외가 발생한다.")
    void throwException_noSuchStudent() {
        // given
        StudentLoginRequest studentLoginRequest = new StudentLoginRequest("hsb422", "123%a");

        // then
        assertThatThrownBy(() -> authService.login(studentLoginRequest.getLoginId(), studentLoginRequest.getPassword()))
                .isInstanceOf(NoSuchStudentException.class)
                .hasMessage(String.format("존재하지 않는 ID 입니다. loginId={%s}", studentLoginRequest.getLoginId()));
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않으면 예외가 발생한다.")
    void throwException_mismatchIdPassword() {
        // given
        String mismatchPassword = "dummy";
        StudentLoginRequest studentLoginRequest = new StudentLoginRequest(student.getLoginId(), mismatchPassword);

        // then
        assertThatThrownBy(() -> authService.login(studentLoginRequest.getLoginId(), studentLoginRequest.getPassword()))
                .isInstanceOf(IdPasswordMismatchException.class)
                .hasMessage("아이디 혹은 비밀번호를 확인해주세요.");
    }

    @Test
    @DisplayName("비밀번호가 일치하여 토큰을 발급한다.")
    void AuthServiceTest() {
        // given
        String matchPassword = "123$a";
        StudentLoginRequest studentLoginRequest = new StudentLoginRequest(student.getLoginId(), matchPassword);


        // when
        TokenResponseDto tokenResponseDto =
                authService.login(studentLoginRequest.getLoginId(), studentLoginRequest.getPassword());

        // then
        assertThat(tokenResponseDto).isNotNull();
    }
}