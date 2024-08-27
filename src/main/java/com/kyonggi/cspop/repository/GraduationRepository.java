package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Graduation;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraduationRepository extends JpaRepository<Graduation, Long> {

    Optional<Graduation> findByStudent(final Student student);
}
