package com.kyonggi.cspop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchDepartmentException extends CspopException {
    public NoSuchDepartmentException(String department) {
        super(
                String.format("존재 하지 않는 학과입니다. 학과 = {%s}", department),
                "존재 하지 않는 학과입니다.",
                HttpStatus.NOT_FOUND,
                "4002"
        );
    }
}
