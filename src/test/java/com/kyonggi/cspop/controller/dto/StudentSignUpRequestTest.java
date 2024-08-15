package com.kyonggi.cspop.controller.dto;

import com.kyonggi.cspop.controller.dto.student.StudentSignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;
import static org.assertj.core.api.Assertions.assertThat;

class StudentSignUpRequestTest extends RequestTest{

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("학번으로 빈 문자열이 들어오면 처리한다.")
    void blankNumber(String number) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                number,
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("아이디로 빈 문자열이 들어오면 처리한다.")
    void blankLoginId(String loginId) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                loginId,
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("비밀번호로 빈 문자열이 들어오면 처리한다.")
    void blankPassword(String password) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                password,
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("생년월일로 빈 문자열이 들어오면 처리한다.")
    void blankBirth(LocalDate birth) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                birth,
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("학과로 빈 문자열이 들어오면 처리한다.")
    void blankDepartment(String department) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                department,
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("학년으로 빈 문자열이 들어오면 처리한다.")
    void blankGrade(String grade) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                grade,
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("전화번호로 빈 문자열이 들어오면 처리한다.")
    void blankPhoneNumber(String phoneNumber) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                phoneNumber,
                "name",
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("학생 이름으로 빈 문자열이 들어오면 처리한다.")
    void blankName(String name) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                name,
                "email",
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이메일로 빈 문자열이 들어오면 처리한다.")
    void blankEmail(String email) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                email,
                "classification"
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("졸업 분류로 빈 문자열이 들어오면 처리한다.")
    void blankClassification(String classification) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                classification
        );

        assertThat(isEmpty(studentSignUpRequest)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1"})
    @DisplayName("15자 초과 학번이 들어오면 처리한다.")
    void invalidNumberSize(String number) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                number,
                "loginId",
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(getConstraintViolation(studentSignUpRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(NUMBER_SIZE_MESSAGE)))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1"})
    @DisplayName("20자 초과 아이디가 들어오면 처리한다.")
    void invalidLoginIdSize(String loginId) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                loginId,
                "password",
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(getConstraintViolation(studentSignUpRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(ID_SIZE_MESSAGE)))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaaaaaaaaaaaaaaa", "1"})
    @DisplayName("10자 초과 학과가 들어오면 처리한다.")
    void invalidDepartmentSize(String department) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                "password",
                LocalDate.now(),
                department,
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(getConstraintViolation(studentSignUpRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(DEPARTMENT_SIZE_MESSAGE)))
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"test!:true", "test1234:true", "1234test:true", "1234!:true", "한글도 실패:true", "123#a2:false"}, delimiter = ':')
    @DisplayName("로그인 비밀번호에 옳지 않는 형식의 문자열이 들어오면 처리한다.")
    void invalidPassword(String password, boolean flag) {
        StudentSignUpRequest studentSignUpRequest = new StudentSignUpRequest(
                "number",
                "loginId",
                password,
                LocalDate.now(),
                "department",
                "grade",
                "phoneNumber",
                "name",
                "email",
                "classification"
        );

        assertThat(getConstraintViolation(studentSignUpRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(PASSWORD_MESSAGE)))
                .isEqualTo(flag);
    }

    private boolean isEmpty(StudentSignUpRequest studentSignUpRequest) {
        return getConstraintViolation(studentSignUpRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(EMPTY_MESSAGE));
    }
}