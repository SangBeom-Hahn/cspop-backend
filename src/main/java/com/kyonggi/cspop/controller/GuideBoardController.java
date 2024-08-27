package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.cspop.controller.dto.guide.GuideBoardUpdateRequest;
import com.kyonggi.cspop.controller.dto.student.LoginStudentRequest;
import com.kyonggi.cspop.service.GuideBoardService;
import com.kyonggi.cspop.service.dto.GuideBoardResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class GuideBoardController {
    private final GuideBoardService guideBoardService;

    @GetMapping("/api/guideboards")
    public ResponseEntity<GuideBoardResponseDto> findGuidanceBoard() {
        return ResponseEntity.ok(
                guideBoardService.findGuidanceBoard()
        );
    }

    @PutMapping("/api/guideboards/admins")
    public ResponseEntity<Void> updateGuidanceBoard(
            @RequestBody @Valid GuideBoardUpdateRequest guideBoardUpdateRequest
    ) {
        guideBoardService.updateGuidanceBoard(guideBoardUpdateRequest.getContent());
        return ResponseEntity.noContent().build();
    }
}