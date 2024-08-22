package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.GuideBoard;
import com.kyonggi.cspop.exception.NotFoundGuideBoardException;
import com.kyonggi.cspop.repository.GuideBoardRepository;
import com.kyonggi.cspop.service.dto.GuideBoardResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GuideBoardService {

    private final GuideBoardRepository guideBoardRepository;

    public GuideBoardResponseDto findGuidanceBoard() {
        GuideBoard guideBoard = guideBoardRepository.findById(1L)
                .orElseThrow(() -> new NotFoundGuideBoardException(1L));

        return GuideBoardResponseDto.from(guideBoard);
    }

    public void updateGuidanceBoard(String content) {
        GuideBoard guideBoard = guideBoardRepository.findById(1L)
                .orElseThrow(() -> new NotFoundGuideBoardException(1L));

        guideBoard.changeContent(content);
    }
}
