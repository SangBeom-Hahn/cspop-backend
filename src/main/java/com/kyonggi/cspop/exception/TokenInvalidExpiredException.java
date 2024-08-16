package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public final class TokenInvalidExpiredException extends CspopException {

    public TokenInvalidExpiredException() {
        super(
                "토큰의 유효기간이 만료됐습니다.",
                "토큰의 유효기간이 만료됐습니다.",
                HttpStatus.UNAUTHORIZED,
                "4012"
        );
    }
}
