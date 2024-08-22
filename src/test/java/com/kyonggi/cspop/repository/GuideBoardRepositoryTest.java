package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.GuideBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class GuideBoardRepositoryTest extends RepositoryTest {

    @Test
    @DisplayName("안내 및 내규 내용을 수정한다.")
    void updateContent() {
        // given
        Long id = guideBoardRepository.save(new GuideBoard("content"))
                .getId();
        String expectedContent = "expectedContent";

        // when
        GuideBoard guideBoard = guideBoardRepository.findById(id)
                .orElseThrow();
        guideBoard.changeContent(expectedContent);

        // then
        assertThat(guideBoard.getContent())
                .isEqualTo(expectedContent);
    }
}