package com.kyonggi.cspop.domain.student;

import lombok.Getter;

@Getter
public enum Department {
    AI("인공지능"),
    CS("컴퓨터공학"),
    SECURITY("융합보안")
    ;

    private final String name;

    Department(String name) {
        this.name = name;
    }
}
