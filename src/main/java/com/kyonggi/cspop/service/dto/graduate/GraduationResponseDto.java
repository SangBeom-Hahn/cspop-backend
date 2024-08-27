package com.kyonggi.cspop.service.dto.graduate;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Department;
import com.kyonggi.cspop.domain.student.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraduationResponseDto {

    private String loginId;

    private String name;

    private Department department;

    private String professorName;

    private Boolean completion;

    private LocalDate date;

    private Status status;

    private Step step;

    public static GraduationResponseDto of(final Student student, final Graduation graduation) {
        return new GraduationResponseDto(
                student.getLoginId(),
                student.getName(),
                student.getDepartment(),
                graduation.getProfessorName(),
                graduation.getCompletion(),
                graduation.getDate(),
                graduation.getStatus(),
                graduation.getStep()
        );
    }
}
