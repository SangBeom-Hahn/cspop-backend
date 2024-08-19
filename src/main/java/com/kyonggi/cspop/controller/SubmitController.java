package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.repository.SubmitRepository;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitResponseDto;
import com.kyonggi.cspop.service.graduate.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
