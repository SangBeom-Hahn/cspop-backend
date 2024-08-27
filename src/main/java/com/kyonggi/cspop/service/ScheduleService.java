package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.schedule.Board;
import com.kyonggi.cspop.domain.schedule.Schedule;
import com.kyonggi.cspop.exception.NoSuchScheduleException;
import com.kyonggi.cspop.repository.ScheduleBoardRepository;
import com.kyonggi.cspop.repository.ScheduleRepository;
import com.kyonggi.cspop.service.dto.schedule.ScheduleBoardResponseDto;
import com.kyonggi.cspop.service.dto.schedule.ScheduleResponseDto;
import com.kyonggi.cspop.service.dto.schedule.SchedulesResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.kyonggi.cspop.utils.validator.CspopConstant.SCHDULE_ID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleBoardRepository scheduleBoardRepository;

    @Transactional(readOnly = true)
    public SchedulesResponseDto findAllSchedule() {
        final List<ScheduleResponseDto> scheduleResponseDtos = scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::from)
                .collect(Collectors.toList());

        final Board board = scheduleBoardRepository.findById(SCHDULE_ID)
                .orElseThrow();

        return SchedulesResponseDto.of(
                scheduleResponseDtos,
                ScheduleBoardResponseDto.from(board)
        );
    }

    public void updateSchedule(final LocalDate startDate, final LocalDate endDate, final Long id) {
        final Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new NoSuchScheduleException(id));
        schedule.changeStart(startDate);
        schedule.changeEnd(endDate);
    }

    public void updateStatus() {
        scheduleRepository.findAll()
                .forEach(Schedule::changeStatus);
    }
}
