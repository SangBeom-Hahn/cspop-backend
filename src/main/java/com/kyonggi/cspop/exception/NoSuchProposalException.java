package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchProposalException extends CspopException {

    public NoSuchProposalException(final Long id) {
        super(
                String.format("제안서 제출을 하지 않은 학생입니다. id = {%d}", id),
                "제안서 제출을 하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "4023"
        );
    }
}
