package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.schedule.ScheduleUpdateRequest;
import com.kyonggi.cspop.domain.schedule.Schedule;
import com.kyonggi.cspop.domain.schedule.Status;
import com.kyonggi.cspop.service.dto.schedule.ScheduleResponseDto;
import com.kyonggi.cspop.service.dto.schedule.SchedulesResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.kyonggi.cspop.domain.schedule.Status.*;
import static com.kyonggi.cspop.domain.schedule.Step.*;
import static com.kyonggi.cspop.domain.schedule.Step.PASS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceTest extends ServiceTest {

    private Schedule schedule1;
    private Schedule schedule2;
    private Schedule schedule3;
    private Schedule schedule4;
    private Schedule schedule5;
    private Schedule schedule6;
    private Schedule schedule7;

    @BeforeEach
    void setUp() {
        schedule1 = new Schedule(
                MIDDLE,
                WAIT,
                LocalDate.now().minusDays(100),
                LocalDate.now().minusDays(1)
        );
        schedule2 = new Schedule(
                PROPOSAL,
                WAIT,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule3 = new Schedule(
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule4 = new Schedule(
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule5 = new Schedule(
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule6 = new Schedule(
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule7 = new Schedule(
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );

        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);
        scheduleRepository.save(schedule4);
        scheduleRepository.save(schedule5);
        scheduleRepository.save(schedule6);
    }

    @Test
    @DisplayName("진행 일정 전체를 조회한다.")
    void findAllSchedule() {
        // given
        SchedulesResponseDto actual = scheduleService.findAllSchedule();
        List<ScheduleResponseDto> scheduleResponseDtos = List.of(
                ScheduleResponseDto.from(schedule1),
                ScheduleResponseDto.from(schedule2),
                ScheduleResponseDto.from(schedule3),
                ScheduleResponseDto.from(schedule4),
                ScheduleResponseDto.from(schedule5),
                ScheduleResponseDto.from(schedule6)
        );

        // when
        SchedulesResponseDto expected = SchedulesResponseDto.from(scheduleResponseDtos);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("진행 일정에서 시작, 끝 날짜를 수정한다.")
    void updateSchedule() {
        // given
        LocalDate changeStart = LocalDate.of(1999, 9, 17);
        LocalDate changeEnd = LocalDate.of(2024, 8, 17);
        ScheduleUpdateRequest scheduleUpdateRequest = new ScheduleUpdateRequest(changeStart, changeEnd);

        // when
        Long saveId = scheduleRepository.save(schedule7)
                .getId();
        scheduleService.updateSchedule(
                scheduleUpdateRequest.getStartDate(),
                scheduleUpdateRequest.getEndDate(),
                saveId
        );
        Schedule schedule = scheduleRepository.findById(saveId)
                .orElseThrow();

        // then
        Assertions.assertThat(schedule)
                .extracting("startDate", "endDate")
                .containsExactly(changeStart, changeEnd);
    }

    @Test
    @DisplayName("진행 일정 상태를 수정한다.")
    void updateStatus() {
        // given
        Status wait = WAIT;
        Status progress = PROGRESS;
        Status finish = FINISH;

        // when
        scheduleService.updateStatus();

        // then
        assertAll(
                () -> assertThat(schedule1.getStatus()).isEqualTo(finish),
                () -> assertThat(schedule2.getStatus()).isEqualTo(progress),
                () -> assertThat(schedule3.getStatus()).isEqualTo(wait),
                () -> assertThat(schedule4.getStatus()).isEqualTo(wait),
                () -> assertThat(schedule5.getStatus()).isEqualTo(wait),
                () -> assertThat(schedule6.getStatus()).isEqualTo(wait)
        );
    }
}