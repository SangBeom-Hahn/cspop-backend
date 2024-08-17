package com.kyonggi.cspop.domain.schedule;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
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
            String receive,
            String proposal,
            String middleReport,
            String finalReport,
            String pass,
            String other
    ) {
        this.receive = receive;
        this.proposal = proposal;
        this.middleReport = middleReport;
        this.finalReport = finalReport;
        this.pass = pass;
        this.other = other;
    }

    public void changeReceive(String receive) {
        this.receive = receive;
    }

    public void changeProposal(String proposal) {
        this.proposal = proposal;
    }

    public void changeMiddleReport(String middleReport) {
        this.middleReport = middleReport;
    }

    public void changeFinalReport(String finalReport) {
        this.finalReport = finalReport;
    }

    public void changeFinalPass(String pass) {
        this.pass = pass;
    }

    public void changeOtherQualification(String other) {
        this.other = other;
    }
}
