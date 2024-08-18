package com.kyonggi.cspop.domain.graduate;

import java.util.Arrays;

public enum Type {

    IMPLEMENT("구현"),
    INVESTIGATE("조사")
    ;

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public static Type from(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
