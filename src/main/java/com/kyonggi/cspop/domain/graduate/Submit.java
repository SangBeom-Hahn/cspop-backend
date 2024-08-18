package com.kyonggi.cspop.domain.graduate;

import com.kyonggi.cspop.domain.BaseEntity;
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
    private String Name;

    @Column(name = "graduate_date", nullable = false)
    private LocalDate graduateDate;

    @Column(name = "capstone_completion")
    private Boolean completion;

    @Column(name = "approve", nullable = false)
    private Boolean approve;

    @Column(name = "reject_reason", length = 100)
    private String reason;

    public Submit(String name, LocalDate graduateDate, Boolean completion, Boolean approve) {
        Name = name;
        this.graduateDate = graduateDate;
        this.completion = completion;
        this.approve = approve;
    }
}
