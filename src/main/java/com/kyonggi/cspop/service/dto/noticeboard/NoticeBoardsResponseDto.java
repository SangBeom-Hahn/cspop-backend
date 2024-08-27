package com.kyonggi.cspop.service.dto.noticeboard;

import com.kyonggi.cspop.controller.dto.PageInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardsResponseDto {

    private List<NoticeBoardListResponseDto> noticeBoardListResponseDtos;

    private PageInfo pageInfo;

    public static NoticeBoardsResponseDto of(final List<NoticeBoardListResponseDto> noticeBoardListResponseDtos, final PageInfo pageInfo) {
        return new NoticeBoardsResponseDto(noticeBoardListResponseDtos, pageInfo);
    }
}
