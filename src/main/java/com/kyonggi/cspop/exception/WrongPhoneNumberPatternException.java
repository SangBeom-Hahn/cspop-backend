package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class WrongPhoneNumberPatternException extends CspopException {
    public WrongPhoneNumberPatternException(String phoneNumber) {
        super(
                String.format("올바르지 않은 패턴입니다. 번호 = {%s}", phoneNumber),
                "올바르지 않은 패턴입니다.",
                HttpStatus.BAD_REQUEST,
                "4004"
        );
    }
}
