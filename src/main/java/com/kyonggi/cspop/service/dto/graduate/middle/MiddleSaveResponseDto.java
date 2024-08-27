package com.kyonggi.cspop.service.dto.graduate.middle;

import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiddleSaveResponseDto {

    private Long id;

    public static MiddleSaveResponseDto from(final Long id) {
        return new MiddleSaveResponseDto(id);
    }
}
