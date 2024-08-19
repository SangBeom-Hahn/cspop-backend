package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Final;
import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinalRepository extends JpaRepository<Final, Long> {

    Optional<Final> findByStudent(Student student);
}
