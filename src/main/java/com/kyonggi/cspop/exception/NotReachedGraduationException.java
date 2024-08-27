package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;


public class NotReachedGraduationException extends CspopException {
    public NotReachedGraduationException(final String birth) {
        super(
                String.format("졸업 날짜는 현재 시점보다 빠를 수 없습니다. 졸업날짜 = {%s}", birth),
                "졸업 날짜는 현재 시점보다 빠를 수 없습니다.",
                HttpStatus.BAD_REQUEST,
                "4026"
        );
    }
}