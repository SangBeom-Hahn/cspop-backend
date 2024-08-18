package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.cspop.controller.dto.graduate.GraduationSaveRequest;
import com.kyonggi.cspop.controller.dto.student.LoginStudentRequest;
import com.kyonggi.cspop.service.dto.graduate.GraduationSaveResponseDto;
import com.kyonggi.cspop.service.graduate.GraduationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/graduates")
@RequiredArgsConstructor
public class GraduationController {

    private final GraduationService graduationService;

    @PostMapping
    public ResponseEntity<GraduationSaveResponseDto> createGraduate(
            @RequestBody @Validated GraduationSaveRequest graduationSaveRequest,
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest
    ) {
        GraduationSaveResponseDto graduationSaveResponseDto = graduationService.saveGraduation(
                graduationSaveRequest.getMethod(),
                loginStudentRequest.getId()
        );
        return ResponseEntity
                .created(URI.create("/api/graduates/" + graduationSaveResponseDto.getId()))
                .body(graduationSaveResponseDto);
    }
}
