package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.guide.GuideBoardUpdateRequest;
import com.kyonggi.cspop.domain.GuideBoard;
import com.kyonggi.cspop.service.dto.GuideBoardResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GuideBoardServiceTest extends ServiceTest {

    private GuideBoard guideBoard;

    @BeforeEach
    void setUp() {
        guideBoard = new GuideBoard("content");
        guideBoardRepository.save(guideBoard);
    }

    @Test
    @DisplayName("저장한 안내 및 내규 게시판 내용을 id로 찾는다.")
    void findGuidanceBoard() {
        // given
        GuideBoardResponseDto guideBoardResponseDto =
                guideBoardService.findGuidanceBoard();

        // then
        assertThat(guideBoardResponseDto).extracting("content")
                .isEqualTo(guideBoard.getContent());
    }
}