package com.kyonggi.cspop.domain.student;

import com.kyonggi.cspop.exception.WrongEmailPatternException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

    @Column(name = "email", nullable = false, length = 30)
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email from(String value) {
        validatePattern(value);
        return new Email(value);
    }

    private static void validatePattern(String value) {
        Matcher matcher = EMAIL_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new WrongEmailPatternException(value);
        }
    }
}
