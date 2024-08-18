package com.kyonggi.cspop.config;

import com.kyonggi.cspop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfig {

    private final ScheduleService scheduleService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSchedulesState() {
        scheduleService.updateStatus();
    }
}
