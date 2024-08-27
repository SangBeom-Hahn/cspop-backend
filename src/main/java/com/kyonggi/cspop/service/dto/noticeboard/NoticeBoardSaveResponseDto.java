package com.kyonggi.cspop.service.dto.noticeboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardSaveResponseDto {

    private Long id;

    public static NoticeBoardSaveResponseDto from(final Long id) {
        return new NoticeBoardSaveResponseDto(id);
    }
}
