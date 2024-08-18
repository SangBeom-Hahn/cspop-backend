package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "final_form")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Final extends BaseEntity {

    @Column(name = "final_form_id")
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

    @Column(name = "page", nullable = false)
    private Integer page;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_final_student"), nullable = false)
    private Student student;

    public Final(Boolean approve, String title, Type type, String qualification, Integer page, Student student) {
        this.approve = approve;
        this.title = title;
        this.type = type;
        this.qualification = qualification;
        this.page = page;
        this.student = student;
    }
}
