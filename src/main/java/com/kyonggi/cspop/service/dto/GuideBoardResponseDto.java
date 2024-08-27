package com.kyonggi.cspop.service.dto;

import com.kyonggi.cspop.domain.GuideBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuideBoardResponseDto {
    private Long id;

    private String content;

    public static GuideBoardResponseDto from(final GuideBoard guideBoard) {
        return new GuideBoardResponseDto(guideBoard.getId(), guideBoard.getContent());
    }
}
