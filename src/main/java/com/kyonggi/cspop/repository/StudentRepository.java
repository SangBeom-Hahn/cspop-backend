package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByNumber(String number);

    boolean existsByLoginId(String loginId);

    Optional<Student> findByLoginId(String loginId);
}
