package com.kyonggi.cspop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CspopException extends RuntimeException {
    private final String showMessage;
    private final HttpStatus status;
    private final String errorCode;

    public CspopException(
            String message,
            String showMessage,
            HttpStatus status,
            String errorCode
    ) {
        super(message);
        this.showMessage = showMessage;
        this.status = status;
        this.errorCode = errorCode;
    }
}
