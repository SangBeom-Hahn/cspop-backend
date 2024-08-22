package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.dto.graduate.MiddleSaveRequest;
import com.kyonggi.cspop.controller.dto.graduate.RejectRequest;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveResponseDto;
import com.kyonggi.cspop.service.graduate.MiddleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/middles")
public class MiddleController {

    private final MiddleService middleService;

    @PostMapping("/{studentId}")
    public ResponseEntity<MiddleSaveResponseDto> save(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated MiddleSaveRequest middleSaveRequest
    ) {
        MiddleSaveResponseDto middleSaveResponseDto = middleService.saveMiddle(middleSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/proposals/" + middleSaveResponseDto.getId()))
                .body(middleSaveResponseDto);
    }

    @PutMapping("/admins/{studentId}/approve")
    public ResponseEntity<Void> approve(@PathVariable("studentId") Long studentId) {
        middleService.approveMiddle(studentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admins/{studentId}/reject")
    public ResponseEntity<Void> reject(
            @PathVariable("studentId") Long studentId,
            @RequestBody @Validated RejectRequest rejectRequest
    ) {
        middleService.rejectMiddle(studentId, rejectRequest.getReason());
        return ResponseEntity.noContent().build();
    }
}
