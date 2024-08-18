package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Method;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.GraduationRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.graduate.GraduationSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.cspop.domain.graduate.Status.UN_APPROVAL;
import static com.kyonggi.cspop.domain.schedule.Step.RECEIVE;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationService {

    private final GraduationRepository graduationRepository;
    private final StudentRepository studentRepository;

    public GraduationSaveResponseDto saveGraduation(String method, Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchStudentIdException(id));
        Graduation graduation = new Graduation(Method.from(method), UN_APPROVAL, RECEIVE, null, null,
                null, student);

        Long saveId = graduationRepository.save(graduation)
                .getId();
        return GraduationSaveResponseDto.from(saveId);
    }
}
