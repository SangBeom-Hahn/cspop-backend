package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.schedule.Schedule;
import com.kyonggi.cspop.exception.NoSuchScheduleException;
import com.kyonggi.cspop.repository.ScheduleRepository;
import com.kyonggi.cspop.service.dto.schedule.ScheduleResponseDto;
import com.kyonggi.cspop.service.dto.schedule.SchedulesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public SchedulesResponseDto findAllSchedule() {
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());

        return SchedulesResponseDto.from(scheduleResponseDtos);
    }

    public void updateSchedule(LocalDate startDate, LocalDate endDate, Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchScheduleException(id));
        schedule.changeStart(startDate);
        schedule.changeEnd(endDate);
    }

    public void updateStatus() {
        scheduleRepository.findAll()
                .forEach(Schedule::changeStatus);
    }
}
