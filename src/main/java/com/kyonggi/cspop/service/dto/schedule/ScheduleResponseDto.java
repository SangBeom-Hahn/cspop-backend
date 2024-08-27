package com.kyonggi.cspop.service.dto.schedule;

import com.kyonggi.cspop.domain.schedule.Schedule;
import com.kyonggi.cspop.domain.schedule.Status;
import com.kyonggi.cspop.domain.schedule.Step;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleResponseDto {

    private Long id;

    private Step step;

    private Status status;

    private LocalDate startDate;

    private LocalDate endDate;

    public static ScheduleResponseDto from(final Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getStep(),
                schedule.getStatus(),
                schedule.getStartDate(),
                schedule.getEndDate()
        );
    }
}
