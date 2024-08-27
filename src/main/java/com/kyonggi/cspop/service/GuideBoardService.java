package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.GuideBoard;
import com.kyonggi.cspop.exception.NotFoundGuideBoardException;
import com.kyonggi.cspop.repository.GuideBoardRepository;
import com.kyonggi.cspop.service.dto.GuideBoardResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.kyonggi.cspop.utils.validator.CspopConstant.BOARD_ID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GuideBoardService {

    private final GuideBoardRepository guideBoardRepository;

    public GuideBoardResponseDto findGuidanceBoard() {
        final GuideBoard guideBoard = guideBoardRepository.findById(BOARD_ID)
                .orElseThrow(() -> new NotFoundGuideBoardException(BOARD_ID));

        return GuideBoardResponseDto.from(guideBoard);
    }

    public void updateGuidanceBoard(final String content) {
        final GuideBoard guideBoard = guideBoardRepository.findById(BOARD_ID)
                .orElseThrow(() -> new NotFoundGuideBoardException(BOARD_ID));

        guideBoard.changeContent(content);
    }
}
