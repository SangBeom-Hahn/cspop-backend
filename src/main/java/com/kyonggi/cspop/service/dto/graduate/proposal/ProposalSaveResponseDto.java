package com.kyonggi.cspop.service.dto.graduate.proposal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProposalSaveResponseDto {

    private Long id;

    public static ProposalSaveResponseDto from(Long id) {
        return new ProposalSaveResponseDto(id);
    }
}
