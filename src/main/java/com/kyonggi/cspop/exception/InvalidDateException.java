package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class InvalidDateException extends CspopException {
    public InvalidDateException(LocalDate date) {
        super(
                String.format("접근할 수 없는 날짜입니다. 날짜 = {%s}", date.toString()),
                "접근할 수 없는 날짜입니다.",
                HttpStatus.NOT_ACCEPTABLE,
                "4019"
        );
    }
}
