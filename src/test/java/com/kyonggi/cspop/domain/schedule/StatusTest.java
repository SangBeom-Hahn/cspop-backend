package com.kyonggi.cspop.domain.schedule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @ParameterizedTest
    @DisplayName("진행 일정 상태를 생성한다.")
    @MethodSource("dates")
    void construct(LocalDate now, LocalDate start, LocalDate end, Status status) {
        Assertions.assertThat(Status.of(now, start, end))
                .isEqualTo(status);
    }

    private static Stream<Arguments> dates() {
        return Stream.of(
                Arguments.of(
                        LocalDate.now(),
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(100),
                        Status.WAIT
                ),
                Arguments.of(
                        LocalDate.now(),
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(100),
                        Status.PROGRESS
                ),
                Arguments.of(
                        LocalDate.now(),
                        LocalDate.now().minusDays(100),
                        LocalDate.now().minusDays(1),
                        Status.FINISH
                )
        );
    }
}