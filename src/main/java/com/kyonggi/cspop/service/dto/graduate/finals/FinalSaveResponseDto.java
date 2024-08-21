package com.kyonggi.cspop.service.dto.graduate.finals;

import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinalSaveResponseDto {
    private Long id;

    public static FinalSaveResponseDto from(Long id) {
        return new FinalSaveResponseDto(id);
    }
}
