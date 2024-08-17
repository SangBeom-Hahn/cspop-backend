package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
