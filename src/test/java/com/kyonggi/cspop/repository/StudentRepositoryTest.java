package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    @DisplayName("존재하지 않는 아이디는 예외가 발생한다.")
    void throwException_noSuchMember() {
        // given
        String notExistedId = "******";

        // when
        Optional<Student> findStudent = studentRepository.findByLoginId(notExistedId);

        // then
        assertThat(findStudent).isEmpty();
    }
    
    @Test
    @DisplayName("학생이 회원가입을 한다.")
    void save() {
        // when
        Student saveStudent = studentRepository.save(student);

        // then
        assertAll(
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
        assertThat(findStudent).usingRecursiveComparison()
                .isEqualTo(student);
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

    @Test
    @DisplayName("이미 존재하는 아이디, 학번인지 검사한다.")
    void checkExist() {
        // given
        studentRepository.save(student);

        // then
        assertAll(
                () -> assertThat(studentRepository.existsByLoginId("123")).isTrue(),
                () -> assertThat(studentRepository.existsByNumber("201811111")).isTrue()
        );
    }

    @Test
    @DisplayName("아이디로 회원을 찾는다.")
    void findByLoginId() {
        // given
        studentRepository.save(student);

        // when
        Student findStudent = studentRepository.findByLoginId(student.getLoginId())
                .orElseThrow();

        // then
        assertThat(findStudent).isEqualTo(student);
    }
}