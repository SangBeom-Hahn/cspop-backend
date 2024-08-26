package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import com.kyonggi.cspop.exception.WrongPhoneNumberPatternException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.PHONE_PATTERN;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {

    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

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
        Matcher matcher = PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new WrongPhoneNumberPatternException(value);
        }
    }
}
