package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.exception.NoSuchSubmitException;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.repository.SubmitRepository;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitResponseDto;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.kyonggi.cspop.domain.schedule.Step.PROPOSAL;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitService {

    private final SubmitRepository submitRepository;
    private final StudentRepository studentRepository;

    public SubmitResponseDto saveSubmit(final Long studentId) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        final Submit submit = new Submit(student.getName(), student);
        final Long id = submitRepository.save(submit)
                .getId();

        return SubmitResponseDto.from(id);
    }

    public void approveSubmit(final SubmitUpdateRequestDto submitUpdateRequestDto, final Long studentId) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        final Submit submit = submitRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchSubmitException(studentId));

        changeSubmit(submitUpdateRequestDto, submit);
        changeGraduation(submitUpdateRequestDto.getGraduateDate(), student);
    }

    private void changeSubmit(final SubmitUpdateRequestDto submitUpdateRequestDto, final Submit submit) {
        submit.changeGraduateDate(submitUpdateRequestDto.getGraduateDate());
        submit.changeApprove(submitUpdateRequestDto.getApprove());
        submit.changeCompletion(submitUpdateRequestDto.getCompletion());
        submit.changeReason(submitUpdateRequestDto.getReason());
    }

    private static void changeGraduation(final LocalDate graduateDate, final Student student) {
        student.changeGraduationStep(PROPOSAL);
        student.changeGraduationSubmit(graduateDate, student.getName());
    }
}
