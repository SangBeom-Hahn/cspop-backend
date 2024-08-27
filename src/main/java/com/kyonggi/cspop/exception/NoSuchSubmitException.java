package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchSubmitException extends CspopException {

    public NoSuchSubmitException(final Long id) {
        super(
                String.format("접수를 하지 않은 학생입니다. id = {%d}", id),
                "접수를 하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4022"
        );
    }
}
