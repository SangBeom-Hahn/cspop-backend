package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthorException extends CspopException {
    public InvalidAuthorException() {
        super(
                "저자가 아니면 변경할 수 없습니다.",
                "저자가 아니면 변경할 수 없습니다.",
                HttpStatus.BAD_REQUEST,
                "4017"
        );
    }
}
