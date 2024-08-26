package com.kyonggi.cspop.domain.schedule;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "schedule_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_board_id")
    private Long id;

    @Column(name = "receive", nullable = false, length = 5000)
    private String receive;

    @Column(name = "proposal", nullable = false, length = 5000)
    private String proposal;

    @Column(name = "middle_report", nullable = false, length = 5000)
    private String middleReport;

    @Column(name = "final_report", nullable = false, length = 5000)
    private String finalReport;

    @Column(name = "pass", nullable = false, length = 5000)
    private String pass;

    @Column(name = "other_qualification", nullable = false)
    private String other;

    public Board(
            final String receive,
            final String proposal,
            final String middleReport,
            final String finalReport,
            final String pass,
            final String other
    ) {
        this.receive = receive;
        this.proposal = proposal;
        this.middleReport = middleReport;
        this.finalReport = finalReport;
        this.pass = pass;
        this.other = other;
    }

    public void changeReceive(final String receive) {
        this.receive = receive;
    }

    public void changeProposal(final String proposal) {
        this.proposal = proposal;
    }

    public void changeMiddleReport(final String middleReport) {
        this.middleReport = middleReport;
    }

    public void changeFinalReport(final String finalReport) {
        this.finalReport = finalReport;
    }

    public void changeFinalPass(final String pass) {
        this.pass = pass;
    }

    public void changeOtherQualification(final String other) {
        this.other = other;
    }
}
