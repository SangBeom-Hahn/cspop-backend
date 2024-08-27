package com.kyonggi.cspop.service.dto.graduate.submit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitResponseDto {

    private Long id;

    public static SubmitResponseDto from(final Long id) {
        return new SubmitResponseDto(id);
    }
}
