package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.cspop.controller.dto.graduate.SubmitUpdateRequest;
import com.kyonggi.cspop.repository.SubmitRepository;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitResponseDto;
import com.kyonggi.cspop.service.graduate.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submits")
public class SubmitController {

    private final SubmitService submitService;

    @PostMapping("/{studentId}")
    public ResponseEntity<SubmitResponseDto> submit(@PathVariable("studentId") Long studentId) {
        SubmitResponseDto submitResponseDto = submitService.saveSubmit(studentId);

        return ResponseEntity
                .created(URI.create("/api/submits/" + submitResponseDto.getId()))
                .body(submitResponseDto);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> approve(
            @RequestBody @Validated SubmitUpdateRequest submitUpdateRequest,
            @PathVariable("studentId") Long studentId
    ) {
        submitService.approveSubmit(submitUpdateRequest.toServiceDto(), studentId);
        return ResponseEntity.noContent().build();
    }
}
