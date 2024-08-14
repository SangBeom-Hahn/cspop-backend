package com.kyonggi.cspop.domain.student;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.kyonggi.cspop.domain.student.RoleType.STUDENT;

@Getter
@Entity
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_number", nullable = false, length = 15)
    private String number;

    @Column(name = "login_id", nullable = false, length = 20)
    private String loginId;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "department", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Grade grade;

    @Embedded
    private PhoneNumber phoneNumber;

    @Column(name = "student_name", nullable = false, length = 10)
    private String name;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    @Column(name = "classification", nullable = false, length = 20)
    private Classification classification;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleType", nullable = false, length = 10)
    private RoleType roleType;

    public Student(
            String number,
            String loginId,
            String password,
            LocalDate birth,
            Department department,
            Grade grade,
            PhoneNumber phoneNumber,
            String name,
            Email email,
            Classification classification
    ) {
        validateLimitBirthDate(birth);
        this.number = number;
        this.loginId = loginId;
        this.password = password;
        this.birth = birth;
        this.department = department;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.classification = classification;
        this.roleType = STUDENT;
    }

    private static void validateLimitBirthDate(LocalDate birth) {
        if (birth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException();
        }
    }
}
