package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidFormException extends CspopException {

    public TokenInvalidFormException() {
        super(
                "올바르지 않은 토큰입니다.",
                "올바르지 않은 토큰입니다.",
                HttpStatus.UNAUTHORIZED,
                "4011"
        );
    }
}
