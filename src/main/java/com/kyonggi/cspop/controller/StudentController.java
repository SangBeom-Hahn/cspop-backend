package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.dto.student.StudentCheckDuplicateIdRequest;
import com.kyonggi.cspop.controller.dto.student.StudentSignUpRequest;
import com.kyonggi.cspop.service.StudentService;
import com.kyonggi.cspop.service.dto.student.StudentSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentSignUpResponseDto> join(@RequestBody @Validated StudentSignUpRequest studentSignUpRequest) {
        final StudentSignUpResponseDto studentSignUpResponseDto =
                studentService.saveStudent(studentSignUpRequest.toServiceDto());
        return ResponseEntity
                .created(URI.create("/api/students" + studentSignUpResponseDto.getId()))
                .body(studentSignUpResponseDto);
    }

    @PostMapping("/validate-duplicate")
    public ResponseEntity<Void> checkDuplicateLoginId(@RequestBody @Validated StudentCheckDuplicateIdRequest studentCheckDuplicateIdRequest) {
        studentService.validateLoginId(studentCheckDuplicateIdRequest.getLoginId());
        return ResponseEntity.noContent().build();
    }
}
