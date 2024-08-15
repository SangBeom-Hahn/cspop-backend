package com.kyonggi.cspop.controller.auth;

import com.kyonggi.cspop.controller.dto.student.StudentLoginRequest;
import com.kyonggi.cspop.service.AuthService;
import com.kyonggi.cspop.service.dto.student.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Validated StudentLoginRequest studentLoginRequest) {
        TokenResponseDto tokenResponseDto = authService.login(studentLoginRequest.getLoginId(), studentLoginRequest.getPassword());
        return ResponseEntity.ok(tokenResponseDto);
    }
}
