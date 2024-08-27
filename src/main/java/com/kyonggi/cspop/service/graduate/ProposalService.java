package com.kyonggi.cspop.service.graduate;

import com.kyonggi.cspop.domain.graduate.Proposal;
import com.kyonggi.cspop.domain.schedule.Step;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.NoSuchProposalException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.exception.NoSuchSubmitException;
import com.kyonggi.cspop.repository.ProposalRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.cspop.domain.schedule.Step.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalService {

    private final StudentRepository studentRepository;
    private final ProposalRepository proposalRepository;

    public ProposalSaveResponseDto saveProposal(
            final ProposalSaveRequestDto proposalSaveRequestDto,
            final Long studentId
    ) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        final Proposal proposal = new Proposal(
                proposalSaveRequestDto.getTitle(),
                proposalSaveRequestDto.getType(),
                proposalSaveRequestDto.getQualification(),
                proposalSaveRequestDto.getContent(),
                student
        );
        final Long saveId = proposalRepository.save(proposal)
                .getId();

        return ProposalSaveResponseDto.from(saveId);
    }

    public void approveProposal(final Long studentId) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        final Proposal proposal = proposalRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchProposalException(studentId));

        student.changeGraduationStep(MIDDLE);
        proposal.changeApprove(true);
    }

    public void rejectProposal(final Long studentId, final String reason) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));
        final Proposal proposal = proposalRepository.findByStudent(student)
                .orElseThrow(() -> new NoSuchProposalException(studentId));

        changeProposal(reason, proposal);
    }

    private void changeProposal(final String reason, final Proposal proposal) {
        proposal.changeApprove(false);
        proposal.changeReason(reason);
    }
}
