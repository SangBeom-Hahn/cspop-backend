package com.kyonggi.cspop.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GuideBoardTest {

    @Test
    @DisplayName("안내 및 내규를 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new GuideBoard("content"));
    }

    @Test
    @DisplayName("안내 및 내규 게시판 내용을 수정한다.")
    void GuidanceBoardTest() {
        // given
        GuideBoard guideBoard = new GuideBoard("content");
        String expectedContent = "expectedContent";

        // when
        guideBoard.changeContent(expectedContent);

        // then
        assertThat(guideBoard.getContent())
                .isEqualTo(expectedContent);
    }
}