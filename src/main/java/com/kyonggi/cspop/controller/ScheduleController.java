package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.dto.schedule.ScheduleUpdateRequest;
import com.kyonggi.cspop.service.ScheduleService;
import com.kyonggi.cspop.service.dto.schedule.SchedulesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<SchedulesResponseDto> findAllSchedule() {
        return ResponseEntity.ok(scheduleService.findAllSchedule());
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody @Validated ScheduleUpdateRequest scheduleUpdateRequest
    ) {
        scheduleService.updateSchedule(
                scheduleUpdateRequest.getStartDate(),
                scheduleUpdateRequest.getEndDate(),
                scheduleId
        );
        return ResponseEntity.noContent().build();
    }
}
