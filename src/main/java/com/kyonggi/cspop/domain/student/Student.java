package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.graduate.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.exception.NotReachedBirthException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.kyonggi.cspop.domain.student.RoleType.STUDENT;

@Getter
@Entity
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_number", nullable = false, length = 15)
    private String number;

    @Column(name = "login_id", nullable = false, length = 20)
    private String loginId;

    @Column(name = "password", nullable = false, length = 60)
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

    @OneToOne(mappedBy = "student")
    private Graduation graduation;

    public Student(
            final String number,
            final String loginId,
            final String password,
            final LocalDate birth,
            final Department department,
            final Grade grade,
            final PhoneNumber phoneNumber,
            final String name,
            final Email email,
            final Classification classification
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

    public Student(
            final Long id,
            final String number,
            final String loginId,
            final String password,
            final LocalDate birth,
            final Department department,
            final Grade grade,
            final PhoneNumber phoneNumber,
            final String name,
            final Email email,
            final Classification classification,
            final RoleType roleType
    ) {
        this.id = id;
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
        this.roleType = roleType;
    }

    private static void validateLimitBirthDate(final LocalDate birth) {
        if (isNotReached(birth)) {
            throw new NotReachedBirthException(birth.toString());
        }
    }

    public void changeGraduationStatus(final Status status) {
        this.graduation.changeStatus(status);
    }

    public void changeGraduationStep(final Step step) {
        this.graduation.changeStep(step);
    }

    public void changeGraduationSubmit(final LocalDate date, final String professorName) {
        this.graduation.changeDate(date);
        this.graduation.changeProfessorName(professorName);
    }

    private static boolean isNotReached(final LocalDate birth) {
        return birth.isAfter(LocalDate.now());
    }

    public boolean isSame(final Long id) {
        return this.id.equals(id);
    }

    public void addGraduation(final Graduation graduation) {
        this.graduation = graduation;
    }
}
