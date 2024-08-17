package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.schedule.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleBoardRepositoryTest extends RepositoryTest {

    @Test
    @DisplayName("진행 일정 게시판을 조회한다.")
    void findById() {
        // given
        Board board = new Board("receive", "proposal", "middleReport", "finalReport",
                "pass", "other");
        Board saveBoard = scheduleBoardRepository.save(board);

        // then
        assertAll(
                () -> assertThat(saveBoard.getId()).isNotNull(),
                () -> assertThat(saveBoard).isEqualTo(board)
        );
    }

    @Test
    @DisplayName("게시판을 수정한다.")
    void updateBoard() {
        // given
        Board board = new Board("receive", "proposal", "middleReport", "finalReport",
                "pass", "other");
        Board saveBoard = scheduleBoardRepository.save(board);
        String pass = "newpass";
        String receive = "newreceive";
        String proposal = "newproposal";
        String middleReport = "newmiddleReport";
        String finalReport = "newfinalReport";
        String other = "newother";

        // when
        saveBoard.changeFinalPass(pass);
        saveBoard.changeFinalReport(finalReport);
        saveBoard.changeMiddleReport(middleReport);
        saveBoard.changeOtherQualification(other);
        saveBoard.changeReceive(receive);
        saveBoard.changeProposal(proposal);
        Board findBoard = scheduleBoardRepository.findById(saveBoard.getId())
                .orElseThrow();

        // then
        assertThat(findBoard)
                .extracting("receive", "proposal", "middleReport", "finalReport", "pass", "other")
                .containsExactly(
                        receive,
                        proposal,
                        middleReport,
                        finalReport,
                        pass,
                        other
                );
    }
}