package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationRepository extends JpaRepository<Graduation, Long> {
}
