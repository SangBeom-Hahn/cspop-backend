package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmitRepository extends JpaRepository<Submit, Long> {

    Optional<Submit> findByStudent(final Student student);
}
