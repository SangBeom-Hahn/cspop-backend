package com.kyonggi.cspop.service.dto.graduate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GraduationSaveResponseDto {

    private Long id;

    public static GraduationSaveResponseDto from(final Long id) {
        return new GraduationSaveResponseDto(id);
    }
}
