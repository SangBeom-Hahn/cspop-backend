package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchScheduleException extends CspopException {
    public NoSuchScheduleException(final Long id) {
        super(
                String.format("존재하지 않는 진행 일정입니다. id = {%d}", id),
                "존재하지 않는 진행 일정입니다.",
                HttpStatus.NOT_FOUND,
                "4020"
        );
    }
}
