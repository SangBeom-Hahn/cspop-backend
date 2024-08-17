package com.kyonggi.cspop.service.dto.schedule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchedulesResponseDto {

    private List<ScheduleResponseDto> scheduleResponseDtos;

    public static SchedulesResponseDto from(List<ScheduleResponseDto> scheduleResponseDtos) {
        return new SchedulesResponseDto(scheduleResponseDtos);
    }
}
