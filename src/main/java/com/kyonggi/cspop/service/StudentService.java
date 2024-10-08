package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.DuplicateLoginIdException;
import com.kyonggi.cspop.exception.DuplicateStudentNumberException;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.student.StudentSignUpRequestDto;
import com.kyonggi.cspop.service.dto.student.StudentSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentSignUpResponseDto saveStudent(final StudentSignUpRequestDto studentSignUpRequestDto) {
        validateDuplicate(studentSignUpRequestDto);
        final String password = passwordEncoder.encode(studentSignUpRequestDto.getPassword());

        final Student Student = new Student(
                studentSignUpRequestDto.getNumber(),
                studentSignUpRequestDto.getLoginId(),
                password,
                studentSignUpRequestDto.getBirth(),
                studentSignUpRequestDto.getDepartment(),
                studentSignUpRequestDto.getGrade(),
                studentSignUpRequestDto.getPhoneNumber(),
                studentSignUpRequestDto.getName(),
                studentSignUpRequestDto.getEmail(),
                studentSignUpRequestDto.getClassification()
        );

        Student saveStudent = studentRepository.save(Student);
        return StudentSignUpResponseDto.from(saveStudent);
    }

    private void validateDuplicate(final StudentSignUpRequestDto studentSignUpRequestDto) {
        validateLoginId(studentSignUpRequestDto.getLoginId());
        validateNumber(studentSignUpRequestDto.getNumber());
    }

    private void validateNumber(final String number) {
        if (studentRepository.existsByNumber(number)) {
            throw new DuplicateStudentNumberException(number);
        }
    }

    public void validateLoginId(final String loginId) {
        if (studentRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException(loginId);
        }
    }
}
