package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByNumber(final String number);

    boolean existsByLoginId(final String loginId);

    Optional<Student> findByLoginId(final String loginId);
}
