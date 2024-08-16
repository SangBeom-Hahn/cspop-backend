package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CspopException{
    public InvalidTokenException() {
        super(
                "존재하지 않는 유저의 토큰입니다. 다시 로그인해주세요.",
                "존재하지 않는 유저의 토큰입니다. 다시 로그인해주세요.",
                HttpStatus.UNAUTHORIZED,
                "4009"
        );
    }
}
