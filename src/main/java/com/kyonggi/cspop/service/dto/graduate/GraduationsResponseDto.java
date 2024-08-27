package com.kyonggi.cspop.service.dto.graduate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraduationsResponseDto {

    private List<GraduationListResponseDto> graduationListResponseDtos;

    public static GraduationsResponseDto from(final List<GraduationListResponseDto> graduationListResponseDtos) {
        return new GraduationsResponseDto(graduationListResponseDtos);
    }
}
