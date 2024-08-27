package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchGradeException extends CspopException {
    public NoSuchGradeException(final String grade) {
        super(
                String.format("존재 하지 않는 학년입니다. 학년 = {%s}", grade),
                "존재 하지 않는 학년입니다.",
                HttpStatus.NOT_FOUND,
                "4008"
        );
    }
}
