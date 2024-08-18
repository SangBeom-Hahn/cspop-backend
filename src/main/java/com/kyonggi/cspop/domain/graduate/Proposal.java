package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "proposal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Proposal extends BaseEntity {

    @Column(name = "proposal_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approve", nullable = false)
    private Boolean approve;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "qualification", nullable = false, length = 45)
    private String qualification;

    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_proposal_student"), nullable = false)
    private Student student;

    public Proposal(Boolean approve, String title, Type type, String qualification, String content, Student student) {
        this.approve = approve;
        this.title = title;
        this.type = type;
        this.qualification = qualification;
        this.content = content;
        this.student = student;
    }
}
