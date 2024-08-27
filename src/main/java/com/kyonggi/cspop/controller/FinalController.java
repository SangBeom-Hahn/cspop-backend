package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.dto.graduate.FinalSaveRequest;
import com.kyonggi.cspop.controller.dto.graduate.MiddleSaveRequest;
import com.kyonggi.cspop.controller.dto.graduate.RejectRequest;
import com.kyonggi.cspop.service.dto.graduate.finals.FinalSaveResponseDto;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveResponseDto;
import com.kyonggi.cspop.service.graduate.FinalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finals")
public class FinalController {

    private final FinalService finalService;

    @PostMapping("/{studentId}")
    public ResponseEntity<FinalSaveResponseDto> save(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated FinalSaveRequest finalSaveRequest
    ) {
        final FinalSaveResponseDto finalSaveResponseDto =
                finalService.saveFinal(finalSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/proposals/" + finalSaveResponseDto.getId()))
                .body(finalSaveResponseDto);
    }

    @PutMapping("/admins/{studentId}/approve")
    public ResponseEntity<Void> approve(@PathVariable("studentId") Long studentId) {
        finalService.approveFinal(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admins/{studentId}/reject")
    public ResponseEntity<Void> reject(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated RejectRequest rejectRequest
    ) {
        finalService.rejectFinal(studentId, rejectRequest.getReason());
        return ResponseEntity.noContent().build();
    }
}
