package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.dto.graduate.RejectRequest;
import com.kyonggi.cspop.controller.dto.graduate.ProposalSaveRequest;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveResponseDto;
import com.kyonggi.cspop.service.graduate.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping("/{studentId}")
    public ResponseEntity<ProposalSaveResponseDto> save(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated ProposalSaveRequest proposalSaveRequest
    ) {
        final ProposalSaveResponseDto proposalSaveResponseDto =
                proposalService.saveProposal(proposalSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/proposals/" + proposalSaveResponseDto.getId()))
                .body(proposalSaveResponseDto);
    }

    @PutMapping("/admins/{studentId}/approve")
    public ResponseEntity<Void> approve(@PathVariable("studentId") Long studentId) {
        proposalService.approveProposal(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admins/{studentId}/reject")
    public ResponseEntity<Void> reject(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated RejectRequest rejectRequest
    ) {
        proposalService.rejectProposal(studentId, rejectRequest.getReason());
        return ResponseEntity.noContent().build();
    }
}
