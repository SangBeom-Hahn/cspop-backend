package com.kyonggi.cspop.domain.schedule;

import com.kyonggi.cspop.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "step", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Step step;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    public Schedule(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Schedule(final Step step, final Status status, final LocalDate startDate, final LocalDate endDate) {
        this.step = step;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void changeStatus() {
        this.status = Status.of(now(), startDate, endDate);
    }

    public void changeStart(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public void changeEnd(final LocalDate endDate) {
        this.endDate = endDate;
    }
}
