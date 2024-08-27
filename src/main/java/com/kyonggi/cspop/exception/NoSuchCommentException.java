package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchCommentException extends CspopException {
    public NoSuchCommentException(final Long id) {
        super(
                String.format("존재하지 않는 댓글입니다. id = {%d}", id),
                "존재하지 않는 댓글입니다.",
                HttpStatus.NOT_FOUND,
                "4018"
        );
    }
}
