package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.exception.NoSuchSubmitException;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.repository.SubmitRepository;
import com.kyonggi.cspop.service.dto.graduate.GraduationResponseDto;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitResponseDto;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.kyonggi.cspop.domain.schedule.Step.PROPOSAL;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitService {

    private final SubmitRepository submitRepository;
    private final StudentRepository studentRepository;

    public SubmitResponseDto saveSubmit(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        Submit submit = new Submit(student.getName(), student);
        Long id = submitRepository.save(submit)
                .getId();

        return SubmitResponseDto.from(id);
    }

    public void approveSubmit(SubmitUpdateRequestDto submitUpdateRequestDto, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        Submit submit = submitRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchSubmitException(studentId));

        changeSubmit(submitUpdateRequestDto, submit);
        changeGraduation(submitUpdateRequestDto, student);
    }

    private void changeSubmit(SubmitUpdateRequestDto submitUpdateRequestDto, Submit submit) {
        submit.changeGraduateDate(submitUpdateRequestDto.getGraduateDate());
        submit.changeApprove(submitUpdateRequestDto.getApprove());
        submit.changeCompletion(submitUpdateRequestDto.getCompletion());
        submit.changeReason(submitUpdateRequestDto.getReason());
    }

    private static void changeGraduation(SubmitUpdateRequestDto submitUpdateRequestDto, Student student) {
        student.changeGraduationStep(PROPOSAL);
        student.changeGraduationSubmit(submitUpdateRequestDto.getGraduateDate(), student.getName());
    }
}
