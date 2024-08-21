package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "middle_form")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Middle extends BaseEntity {

    @Column(name = "middle_form_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approve")
    private Boolean approve;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @Column(name = "plan", nullable = false, length = 500)
    private String plan;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_middle_student"), nullable = false)
    private Student student;

    public Middle(String title, Type type, String text, String plan, Student student) {
        this.title = title;
        this.type = type;
        this.text = text;
        this.plan = plan;
        this.student = student;
    }

    public void changeApprove(Boolean approve) {
        this.approve = approve;
    }

    public void changeReason(String reason) {
        this.reason = reason;
    }
}
