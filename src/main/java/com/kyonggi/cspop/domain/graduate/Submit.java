package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "submit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Submit extends BaseEntity {

    @Column(name = "submit_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "professor_name", nullable = false, length = 10)
    private String name;

    @Column(name = "graduate_date")
    private LocalDate graduateDate;

    @Column(name = "capstone_completion")
    private Boolean completion;

    @Column(name = "approve")
    private Boolean approve;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_submit_student"), nullable = false)
    private Student student;

    public Submit(final String name, final Student student) {
        this.name = name;
        this.student = student;
    }

    public void changeGraduateDate(final LocalDate graduateDate) {
        this.graduateDate = graduateDate;
    }

    public void changeApprove(final Boolean approve) {
        this.approve = approve;
    }

    public void changeCompletion(final Boolean completion) {
        this.completion = completion;
    }

    public void changeReason(final String reason) {
        this.reason = reason;
    }
}
