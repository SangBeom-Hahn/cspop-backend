package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Final;
import com.kyonggi.cspop.domain.graduate.Middle;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchFinalException;
import com.kyonggi.cspop.exception.NoSuchMiddleException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.FinalRepository;
import com.kyonggi.cspop.repository.MiddleRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.graduate.finals.FinalSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.finals.FinalSaveResponseDto;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.cspop.domain.schedule.Step.PASS;

@Service
@Transactional
@RequiredArgsConstructor
public class FinalService {

    private final StudentRepository studentRepository;
    private final FinalRepository finalRepository;

    public FinalSaveResponseDto saveFinal(
            FinalSaveRequestDto finalSaveRequestDto,
            Long studentId
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        Final saveFinal = new Final(
                finalSaveRequestDto.getTitle(),
                finalSaveRequestDto.getType(),
                finalSaveRequestDto.getQualification(),
                finalSaveRequestDto.getPage(),
                student
        );

        Long saveId = finalRepository.save(saveFinal)
                .getId();

        return FinalSaveResponseDto.from(saveId);
    }

    public void approveFinal(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        Final findFinal = finalRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchFinalException(studentId));

        changeFinal(student);
        findFinal.changeApprove(true);
    }

    private void changeFinal(Student student) {
        student.changeGraduationStep(PASS);
        student.changeGraduationStatus(Status.APPROVAL);
    }

    public void rejectFinal(Long studentId, String reason) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        Final findFinal = finalRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchFinalException(studentId));

        changeFinal(reason, findFinal);
    }

    private void changeFinal(String reason, Final findFinal) {
        findFinal.changeApprove(false);
        findFinal.changeReason(reason);
    }
}
