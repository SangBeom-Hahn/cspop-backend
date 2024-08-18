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

    @Column(name = "approve", nullable = false)
    private Boolean approve;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "type", nullable = false)
    private Type type;

    // text
    @Column(name = "text", nullable = false, length = 500)
    private String text;

    // plan
    @Column(name = "plan", nullable = false, length = 500)
    private String plan;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_middle_student"), nullable = false)
    private Student student;

    public Middle(Boolean approve, String title, Type type, String text, String plan, Student student) {
        this.approve = approve;
        this.title = title;
        this.type = type;
        this.text = text;
        this.plan = plan;
        this.student = student;
    }
}
