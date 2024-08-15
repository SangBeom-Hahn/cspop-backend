package com.kyonggi.cspop.service.dto.student;

import com.kyonggi.cspop.domain.student.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudentSignUpRequestDto {

    private String number;

    private String loginId;

    private String password;

    private LocalDate birth;

    private Department department;

    private Grade grade;

    private PhoneNumber phoneNumber;

    private String name;

    private Email email;

    private Classification classification;
}
