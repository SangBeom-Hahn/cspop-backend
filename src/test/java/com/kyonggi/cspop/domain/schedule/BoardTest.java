package com.kyonggi.cspop.domain.schedule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    @DisplayName("진행 일정 게시판을 수정한다.")
    void changeScheduleBoard() {
        // given
        Board board = new Board("receive", "proposal", "middleReport", "finalReport",
                "pass", "other");
        String pass = "pass";
        String receive = "receive";
        String proposal = "proposal";
        String middleReport = "middleReport";
        String finalReport = "finalReport";
        String other = "other";

        // when
        board.changeFinalPass(pass);
        board.changeFinalReport(finalReport);
        board.changeMiddleReport(middleReport);
        board.changeOtherQualification(other);
        board.changeReceive(receive);
        board.changeProposal(proposal);

        // then
        assertAll(
                () -> assertThat(board.getReceive()).isEqualTo(receive),
                () -> assertThat(board.getPass()).isEqualTo(pass),
                () -> assertThat(board.getProposal()).isEqualTo(proposal),
                () -> assertThat(board.getMiddleReport()).isEqualTo(middleReport),
                () -> assertThat(board.getFinalReport()).isEqualTo(finalReport),
                () -> assertThat(board.getOther()).isEqualTo(other)
        );
    }

    @Test
    @DisplayName("진행 일정 게시판을 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new Board("receive", "proposal", "middleReport", "finalReport",
                "pass", "other"));
    }
}