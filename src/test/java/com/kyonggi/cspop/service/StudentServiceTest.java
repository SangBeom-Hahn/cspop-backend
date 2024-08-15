package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.DuplicateLoginIdException;
import com.kyonggi.cspop.exception.DuplicateStudentNumberException;
import com.kyonggi.cspop.service.dto.student.StudentSignUpRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class StudentServiceTest extends ServiceTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(
                "201811111",
                "125",
                "111&!a",
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
    @DisplayName("이미 존재하는 학번은 예외가 발생한다.")
    void throwException_duplicateStudentNumber() {
        // given
        StudentSignUpRequestDto signUpRequestDto = createDuplicateSignUpRequestDto();

        // then
        assertThatThrownBy(() -> studentService.saveStudent(signUpRequestDto))
                .isInstanceOf(DuplicateLoginIdException.class)
                .hasMessage(String.format("이미 존재하는 ID입니다. ID = {%s}", signUpRequestDto.getLoginId()));
    }

    @Test
    @DisplayName("이미 존재하는 아이디는 예외가 발생한다.")
    void throwException_duplicateLoginId() {
        // given
        StudentSignUpRequestDto signUpRequestDto = createDuplicateSignUpRequestDto();

        // then
        assertThatThrownBy(() -> studentService.saveStudent(signUpRequestDto))
                .isInstanceOf(DuplicateLoginIdException.class)
                .hasMessage(String.format("이미 존재하는 ID입니다. ID = {%s}", signUpRequestDto.getLoginId()));
    }

    @Test
    @DisplayName("학생이 회원가입을 성공하고, id로 찾는다.")
    void saveStudentAndFind() {
        // given
        StudentSignUpRequestDto signUpRequestDto = createNewSignRequestDto();
        Long saveId = studentService.saveStudent(signUpRequestDto)
                .getId();

        // when
        Student findStudent = studentRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findStudent).extracting("id", "number", "loginId", "birth", "department", "grade", "phoneNumber",
                "name", "email", "classification", "roleType")
                .containsExactly(
                        saveId,
                        "201822222",
                        "1235",
                        LocalDate.of(1999, 9, 17),
                        Department.AI,
                        Grade.FIRTH,
                        findStudent.getPhoneNumber(),
                        "dummy",
                        findStudent.getEmail(),
                        Classification.UNDERGRADUATE_STUDENT,
                        RoleType.STUDENT
                );
    }

    @Test
    @DisplayName("Password Encoder를 사용하여 비밀번호를 인코딩한다.")
    void matchPasswordEncode() {
        // given
        StudentSignUpRequestDto studentSignUpRequestDto = createNewSignRequestDto();

        // when
        Long saveId = studentService.saveStudent(studentSignUpRequestDto)
                .getId();
        Student findStudent = studentRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(passwordEncoder.matches(studentSignUpRequestDto.getPassword(), findStudent.getPassword()))
                .isTrue();
    }

    private StudentSignUpRequestDto createDuplicateSignUpRequestDto() {
        return new StudentSignUpRequestDto(
                "201811111",
                "123",
                "111&!a",
                LocalDate.of(1999, 9, 17),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        );
    }

    private StudentSignUpRequestDto createNewSignRequestDto() {
        return new StudentSignUpRequestDto(
                "201822222",
                "1235",
                "111&!a",
                LocalDate.of(1999, 9, 17),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        );
    }
}