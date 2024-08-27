package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchFinalException extends CspopException {

    public NoSuchFinalException(final Long id) {
        super(
                String.format("최종보고서 제출을 하지 않은 학생입니다. id = {%d}", id),
                "최종보고서 제출을 하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4025"
        );
    }
}
