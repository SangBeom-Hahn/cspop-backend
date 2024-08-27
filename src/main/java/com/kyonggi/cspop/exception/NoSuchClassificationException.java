package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchClassificationException extends CspopException {
    public NoSuchClassificationException(final String classification) {
        super(
                String.format("존재하지 않은 학년 분류입니다. 분류 = {%s}", classification),
                "존재하지 않은 학년 분류입니다.",
                HttpStatus.NOT_FOUND,
                "4001"
        );
    }
}
