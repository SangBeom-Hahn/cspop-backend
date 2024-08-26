package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import com.kyonggi.cspop.exception.WrongEmailPatternException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMAIL_PATTERN;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    @Column(name = "email", nullable = false, length = 30)
    private String value;

    private Email(final String value) {
        this.value = value;
    }

    public static Email from(final String value) {
        validatePattern(value);
        return new Email(value);
    }

    private static void validatePattern(final String value) {
        Matcher matcher = PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new WrongEmailPatternException(value);
        }
    }
}
