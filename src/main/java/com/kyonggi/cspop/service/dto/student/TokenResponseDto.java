package com.kyonggi.cspop.service.dto.student;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {

    private Long id;

    private String accessToken;

    private String refreshToken;

    public static TokenResponseDto of(String accessToken, String refreshToken, Long id) {
        return new TokenResponseDto(id, accessToken, refreshToken);
    }
}
