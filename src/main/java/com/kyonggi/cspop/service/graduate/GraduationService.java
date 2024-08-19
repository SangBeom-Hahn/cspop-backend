package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Method;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchGraduationException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.GraduationRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.graduate.GraduationListResponseDto;
import com.kyonggi.cspop.service.dto.graduate.GraduationResponseDto;
import com.kyonggi.cspop.service.dto.graduate.GraduationSaveResponseDto;
import com.kyonggi.cspop.service.dto.graduate.GraduationsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public GraduationsResponseDto findAllGraduation() {
        List<GraduationListResponseDto> graduationListResponseDtos = graduationRepository.findAll()
                .stream()
                .map(graduation -> GraduationListResponseDto.of(graduation.getStudent(), graduation))
                .toList();

        return GraduationsResponseDto.from(graduationListResponseDtos);
    }

    public GraduationResponseDto findGraduation(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        Graduation graduation = graduationRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchGraduationException(studentId));
        return GraduationResponseDto.of(student, graduation);
    }
}
