package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchStudentException extends CspopException {
    public NoSuchStudentException(final String loginId) {
        super(
                String.format("존재하지 않는 ID 입니다. loginId={%s}", loginId),
                "존재하지 않는 ID 입니다.",
                HttpStatus.NOT_FOUND,
                "4015"
        );
    }
}
