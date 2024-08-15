package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.NoSuchDepartmentException;
import lombok.Getter;

import java.util.Arrays;

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

    public static Department from(String name) {
        return Arrays.stream(values())
                .filter(department -> department.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchDepartmentException(name));
    }
}
