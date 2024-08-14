package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
