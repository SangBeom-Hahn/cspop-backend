package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchClassificationException;

import java.util.Arrays;

public enum Classification {
    UNDERGRADUATE_STUDENT("학부생"),
    POSTGRADUATE_STUDENT("대학원생"),
    PROFESSOR("교수"),
    DOUBLE_MAJOR("복수전공"),
    ;

    private final String desc;

    Classification(String desc) {
        this.desc = desc;
    }

    public static Classification from(String desc) {
        return Arrays.stream(values())
                .filter(classification -> classification.desc.equals(desc))
                .findFirst()
                .orElseThrow(() -> new NoSuchClassificationException(desc));
    }
}
