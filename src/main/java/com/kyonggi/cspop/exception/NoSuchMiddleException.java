package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchMiddleException extends CspopException {

    public NoSuchMiddleException(Long id) {
        super(
                String.format("중간보고서 제출을 하지 않은 학생입니다. id = {%d}", id),
                "중간보고서 제출을 하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4024"
        );
    }
}
