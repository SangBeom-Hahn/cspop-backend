package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NotReachedBirthException extends CspopException {
    public NotReachedBirthException(String birth) {
        super(
                String.format("생년월일은 현재 시점보다 늦을 수 없습니다. 생년월일 = {%s}", birth),
                "생년월일은 현재 시점보다 늦을 수 없습니다.",
                HttpStatus.BAD_REQUEST,
                "4003"
        );
    }
}
