package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest extends RepositoryTest{

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(
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
    
    @Test
    @DisplayName("학생이 회원가입을 한다.")
    void save() {
        // when
        Student saveStudent = studentRepository.save(student);

        // then
        Assertions.assertAll(
                () -> assertThat(saveStudent.getId()).isNotNull(),
                () -> assertThat(saveStudent).isEqualTo(student)
        );
    }

    @Test
    @DisplayName("학생을 pk로 조회한다.")
    void findById() {
        // given
        Long saveId = studentRepository.save(student)
                .getId();

        // when
        Student findStudent = studentRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findStudent).extracting(
                "id", "number", "loginId", "password", "birth", "department", "grade", "phoneNumber",
                "name", "email", "classification", "roleType")
                .containsExactly(
                        saveId,
                        "201811111",
                        "123",
                        "111&!a",
                        LocalDate.of(1999, 9, 17),
                        Department.AI,
                        Grade.FIRTH,
                        student.getPhoneNumber(),
                        "dummy",
                        student.getEmail(),
                        Classification.UNDERGRADUATE_STUDENT,
                        RoleType.STUDENT
                );
    }

    @Test
    @DisplayName("학생 정보를 삭제한다.")
    void deleteById() {
        // given
        Long saveId = studentRepository.save(student)
                .getId();

        // when
        studentRepository.deleteById(saveId);

        // then
        assertThat(studentRepository.findById(saveId)).isEmpty();
    }
}