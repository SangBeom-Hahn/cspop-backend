package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchGradeException;

import java.util.Arrays;

public enum Grade {
    FIRST("1학년"),
    SECOND("2학년"),
    THIRD("3학년"),
    FIRTH("4학년")
    ;

    private final String value;

    Grade(String value) {
        this.value = value;
    }

    public static Grade from(String value) {
        return Arrays.stream(values())
                .filter(grade -> grade.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchGradeException(value));
    }
}
