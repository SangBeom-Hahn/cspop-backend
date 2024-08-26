package com.kyonggi.cspop.domain.graduate;

import java.util.Arrays;

public enum Status {

    APPROVAL("승인"),
    UN_APPROVAL("미승인"),
    REJECT("반려");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public static Status from(final String name) {
        return Arrays.stream(values())
                .filter(status -> status.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
