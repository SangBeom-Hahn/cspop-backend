package com.kyonggi.cspop.service.dto.student;

import com.kyonggi.cspop.domain.student.Student;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentSignUpResponseDto {

    private Long id;

    public static StudentSignUpResponseDto from(Student student) {
        return new StudentSignUpResponseDto(student.getId());
    }
}
