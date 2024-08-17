package com.kyonggi.cspop.domain.schedule;

import com.kyonggi.cspop.exception.InvalidDateException;
import lombok.Getter;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
public enum Status {

    WAIT("대기", (now, start, end) -> now.isBefore(start)),
    PROGRESS("진행중", (now, start, end) -> now.isAfter(start) && now.isBefore(end)),
    FINISH("마감", (now, start, end) -> now.isAfter(end))
    ;

    private final String name;

    private final TriPredicate<LocalDate, LocalDate, LocalDate> predicate;

    Status(String name, TriPredicate<LocalDate, LocalDate, LocalDate> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public static Status of(LocalDate now, LocalDate start, LocalDate end) {
        return Arrays.stream(values())
                .filter(status -> status.predicate.test(now, start, end))
                .findFirst()
                .orElseThrow(() -> new InvalidDateException(now));
    }
}
