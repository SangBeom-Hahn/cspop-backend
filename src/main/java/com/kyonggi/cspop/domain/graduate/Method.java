package com.kyonggi.cspop.domain.graduate;

import java.util.Arrays;

public enum Method {

    THESIS("논문"),
    OTHER("기타")
    ;

    private final String name;

    Method(String name) {
        this.name = name;
    }

    public static Method from(final String name) {
        return Arrays.stream(values())
                .filter(method -> method.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
