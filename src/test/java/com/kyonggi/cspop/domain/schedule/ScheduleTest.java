package com.kyonggi.cspop.domain.schedule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    @ParameterizedTest
    @DisplayName("시작, 끝 날짜에 따라서 현재 상태를 변경한다.")
    @MethodSource("dates")
    void changeStatus(LocalDate start, LocalDate end, Status status) {
        // given
        Schedule schedule = new Schedule(start, end);

        // when
        schedule.changeStatus();

        // then
        assertThat(schedule.getStatus())
                .isEqualTo(status);
    }

    @Test
    @DisplayName("")
    void test() {
        //given
        final Schedule schedule = new Schedule(LocalDate.now().plusDays(1), LocalDate.now().plusDays(100));

        //when
        schedule.changeEnd(LocalDate.now());

        //then

    }

    private static Stream<Arguments> dates() {
        return Stream.of(
                Arguments.of(
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(100),
                        Status.WAIT
                ),
                Arguments.of(
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(100),
                        Status.PROGRESS
                ),
                Arguments.of(
                        LocalDate.now().minusDays(100),
                        LocalDate.now().minusDays(1),
                        Status.FINISH
                )
        );
    }

    @Test
    @DisplayName("스케줄 시작, 끝 날짜를 수정한다.")
    void changeDate() {
        // given
        LocalDate changeStartDate = LocalDate.now();
        LocalDate changeEndDate = LocalDate.now();
        Schedule schedule = new Schedule(LocalDate.MIN, LocalDate.MAX);

        // when
        schedule.changeStart(changeStartDate);
        schedule.changeEnd(changeEndDate);

        // then
        assertAll(
                () -> assertThat(schedule.getStartDate()).isEqualTo(changeStartDate),
                () -> assertThat(schedule.getEndDate()).isEqualTo(changeEndDate)
        );
    }
}