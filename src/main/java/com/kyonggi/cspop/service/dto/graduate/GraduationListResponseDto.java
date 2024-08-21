package com.kyonggi.cspop.service.dto.graduate;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraduationListResponseDto {

    private String loginId;

    private String name;

    private String professorName;

    private LocalDate date;

    private Step step;

    private Status status;

    private Boolean completion;

    private Long studentId;

    public static GraduationListResponseDto of(Student student, Graduation graduation) {
        return new GraduationListResponseDto(
                student.getLoginId(),
                student.getName(),
                graduation.getProfessorName(),
                graduation.getDate(),
                graduation.getStep(),
                graduation.getStatus(),
                graduation.getCompletion(),
                student.getId()
        );
    }
}
