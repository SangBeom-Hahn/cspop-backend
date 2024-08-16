package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchNoticeException extends CspopException {
    public NoSuchNoticeException(Long id) {
        super(
                String.format("존재하지 않은 공지사항입니다. id = {%d}", id),
                "존재하지 않은 공지사항입니다.",
                HttpStatus.NOT_FOUND,
                "4016"
        );
    }
}
