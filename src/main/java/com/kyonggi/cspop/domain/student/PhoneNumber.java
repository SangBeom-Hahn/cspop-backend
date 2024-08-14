package com.kyonggi.cspop.domain.student;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");

    @Column(name = "phone_number", nullable = false, length = 20)
    private String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber from(String value) {
        validatePattern(value);
        return new PhoneNumber(value);
    }

    private static void validatePattern(String value) {
        Matcher matcher = PHONE_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }
}
