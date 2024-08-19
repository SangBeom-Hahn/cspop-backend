package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.graduate.Proposal;
import com.kyonggi.cspop.domain.graduate.Submit;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    Optional<Proposal> findByStudent(Student student);
}
