package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchStudentIdException extends CspopException{
    public NoSuchStudentIdException(final Long id) {
        super(
                String.format("가입되지 않은 학생입니다. studentId={%d}", id),
                "가입되지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4014"
        );
    }
}
