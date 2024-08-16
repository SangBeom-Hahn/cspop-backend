package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateStudentNumberException extends CspopException {
    public DuplicateStudentNumberException(String number) {
        super(
                String.format("이미 존재하는 학번입니다. 학번 = {%s}", number),
                "이미 존재하는 학번입니다.",
                HttpStatus.BAD_REQUEST,
                "4006"
        );
    }
}
