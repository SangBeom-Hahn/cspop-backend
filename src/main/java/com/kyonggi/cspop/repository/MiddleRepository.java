package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Middle;
import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MiddleRepository extends JpaRepository<Middle, Long> {

    Optional<Middle> findByStudent(final Student student);
}
