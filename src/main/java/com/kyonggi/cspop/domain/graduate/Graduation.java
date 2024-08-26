package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;

import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NotReachedGraduationException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@Entity
@Getter
@Table(name = "graduation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Graduation extends BaseEntity {

    @Column(name = "graduation_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "step")
    @Enumerated(EnumType.STRING)
    private Step step;

    @Column(name = "capstone_completion")
    private Boolean completion;

    @Column(name = "graduate_date")
    private LocalDate date;

    @Column(name = "professor_name", length = 10)
    private String professorName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_graduation_student"), nullable = false)
    private Student student;

    public Graduation(
            final Method method,
            final Status status,
            final Step step,
            final Boolean completion,
            final LocalDate date,
            final String professorName,
            final Student student
    ) {
        validateDate(date);
        this.method = method;
        this.status = status;
        this.step = step;
        this.completion = completion;
        this.date = date;
        this.professorName = professorName;
        this.student = student;
        this.student.addGraduation(this);
    }

    private static void validateDate(final LocalDate date) {
        if (date.isBefore(now())) {
            throw new NotReachedGraduationException(date.toString());
        }
    }

    public void changeStatus(final Status status) {
        this.status = status;
    }

    public void changeStep(final Step step) {
        this.step = step;
    }

    public void changeDate(final LocalDate date) {
        this.date = date;
    }

    public void changeProfessorName(final String professorName) {
        this.professorName = professorName;
    }
}
