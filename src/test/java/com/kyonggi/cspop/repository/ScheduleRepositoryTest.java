package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.schedule.Schedule;
import com.kyonggi.cspop.domain.schedule.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.kyonggi.cspop.domain.schedule.Status.*;
import static com.kyonggi.cspop.domain.schedule.Step.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setAllowComparingPrivateFields;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleRepositoryTest extends RepositoryTest {

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
                RECEIVE,
                WAIT,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule2 = new Schedule(
                PROPOSAL,
                WAIT,
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(100)
        );
        schedule3 = new Schedule(
                MIDDLE,
                WAIT,
                LocalDate.now().minusDays(100),
                LocalDate.now().minusDays(1)
        );
        schedule4 = new Schedule(
                FINAL,
                WAIT,
                LocalDate.MIN,
                LocalDate.MAX
        );
        schedule5 = new Schedule(
                OTHER_QUALIFICATION,
                WAIT,
                LocalDate.MIN,
                LocalDate.MAX
        );
        schedule6 = new Schedule(
                PASS,
                WAIT,
                LocalDate.MIN,
                LocalDate.MAX
        );
        schedule7 = new Schedule(
                PASS,
                WAIT,
                LocalDate.MIN,
                LocalDate.MAX
        );
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
        scheduleRepository.save(schedule3);
        scheduleRepository.save(schedule4);
        scheduleRepository.save(schedule5);
        scheduleRepository.save(schedule6);
    }

    @Test
    @DisplayName("진행 일정을 전체 조회한다.")
    void findAll() {
        // given
        List<Schedule> expected = List.of(
                schedule1,
                schedule2,
                schedule3,
                schedule4,
                schedule5,
                schedule6
        );

        // when
        List<Schedule> schedules = scheduleRepository.findAll();

        // then
        assertAll(
                () -> assertThat(schedules).hasSize(6),
                () -> assertThat(schedules)
                        .usingRecursiveComparison()
                        .isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("진행 일정의 시작, 끝 날짜를 수정한다.")
    void changeSchedule() {
        // given
        LocalDate changeStartDate = LocalDate.now();
        LocalDate changeEndDate = LocalDate.now();
        Long saveId = scheduleRepository.save(schedule7)
                .getId();

        // when
        schedule7.changeStart(changeStartDate);
        schedule7.changeEnd(changeEndDate);
        Schedule findSchedule = scheduleRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findSchedule)
                .extracting("startDate", "endDate")
                .containsExactly(changeStartDate, changeEndDate);
    }

    @Test
    @DisplayName("시작, 끝 날짜에 따라서 현재 상태를 수정한다.")
    void changeStatus() {
        // given
        Status expected1 = WAIT;
        Status expected2 = PROGRESS;
        Status expected3 = FINISH;

        // when
        schedule1.changeStatus();
        schedule2.changeStatus();
        schedule3.changeStatus();
        Schedule actual1 = scheduleRepository.findById(schedule1.getId())
                .orElseThrow();
        Schedule actual2 = scheduleRepository.findById(schedule2.getId())
                .orElseThrow();
        Schedule actual3 = scheduleRepository.findById(schedule3.getId())
                .orElseThrow();

        // then
        assertAll(
                () -> assertThat(actual1.getStatus()).isEqualTo(expected1),
                () -> assertThat(actual2.getStatus()).isEqualTo(expected2),
                () -> assertThat(actual3.getStatus()).isEqualTo(expected3)
        );
    }
}