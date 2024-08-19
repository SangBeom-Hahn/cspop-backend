package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchGraduationException extends CspopException {
    public NoSuchGraduationException(Long id) {
        super(
                String.format("졸업을 신청하지 않은 학생입니다. id = {%d}", id),
                "졸업을 신청하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4021"
        );
    }
}
