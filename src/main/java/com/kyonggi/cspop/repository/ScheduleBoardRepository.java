package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.schedule.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleBoardRepository extends JpaRepository<Board, Long> {
}
