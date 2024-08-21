package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Middle;
import com.kyonggi.cspop.domain.graduate.Proposal;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchMiddleException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.MiddleRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.cspop.domain.schedule.Step.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MiddleService {

    private final StudentRepository studentRepository;
    private final MiddleRepository middleRepository;

    public MiddleSaveResponseDto saveMiddle(
            MiddleSaveRequestDto middleSaveRequestDto,
            Long studentId
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        Middle middle = new Middle(
                middleSaveRequestDto.getTitle(),
                middleSaveRequestDto.getType(),
                middleSaveRequestDto.getText(),
                middleSaveRequestDto.getPlan(),
                student
        );

        Long saveId = middleRepository.save(middle)
                .getId();

        return MiddleSaveResponseDto.from(saveId);
    }

    public void approveMiddle(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        Middle middle = middleRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchMiddleException(studentId));

        student.changeGraduationStep(FINAL);
        middle.changeApprove(true);
    }

    public void rejectMiddle(Long studentId, String reason) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        Middle middle = middleRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchMiddleException(studentId));

        changeMiddle(reason, middle);
    }

    private void changeMiddle(String reason, Middle middle) {
        middle.changeApprove(false);
        middle.changeReason(reason);
    }
}
