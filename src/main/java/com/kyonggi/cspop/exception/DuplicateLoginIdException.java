package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateLoginIdException extends CspopException {
    public DuplicateLoginIdException (String loginId) {
        super(
                String.format("이미 존재하는 ID입니다. ID = {%s}", loginId),
                "이미 존재하는 ID입니다.",
                HttpStatus.BAD_REQUEST,
                "4007"
        );
    }
}
