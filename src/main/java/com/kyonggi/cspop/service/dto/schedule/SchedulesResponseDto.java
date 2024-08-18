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

    private ScheduleBoardResponseDto scheduleBoardResponseDto;

    public static SchedulesResponseDto of(
            List<ScheduleResponseDto> scheduleResponseDtos,
            ScheduleBoardResponseDto scheduleBoardResponseDto
    ) {
        return new SchedulesResponseDto(scheduleResponseDtos, scheduleBoardResponseDto);
    }
}
