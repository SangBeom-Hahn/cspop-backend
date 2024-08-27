package com.kyonggi.cspop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CspopException extends RuntimeException {
    private final String showMessage;
    private final HttpStatus status;
    private final String errorCode;

    public CspopException(
            final String message,
            final String showMessage,
            final HttpStatus status,
            final String errorCode
    ) {
        super(message);
        this.showMessage = showMessage;
        this.status = status;
        this.errorCode = errorCode;
    }
}
