package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class WrongEmailPatternException extends CspopException {
    public WrongEmailPatternException(String email) {
        super(
                String.format("올바르지 않은 이메일 패턴입니다. 이메일 = {%s}", email),
                "올바르지 않은 이메일 패턴입니다.",
                HttpStatus.BAD_REQUEST,
                "4005"
        );
    }
}
