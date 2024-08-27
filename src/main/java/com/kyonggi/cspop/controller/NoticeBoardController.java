package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.cspop.controller.dto.noticeboard.NoticeBoardSaveRequest;
import com.kyonggi.cspop.controller.dto.noticeboard.NoticeBoardUpdateRequest;
import com.kyonggi.cspop.controller.dto.student.LoginStudentRequest;
import com.kyonggi.cspop.service.NoticeBoardService;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardListResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardReadResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardSaveResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/noticeboards")
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @PostMapping("/admin")
    public ResponseEntity<NoticeBoardSaveResponseDto> create(
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest,
            @RequestBody @Validated NoticeBoardSaveRequest noticeBoardSaveRequest
    ) {
        final NoticeBoardSaveResponseDto noticeBoardSaveResponseDto = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                loginStudentRequest.getId()
        );
        return ResponseEntity
                .created(URI.create("/api/noticeboards/admin/" + noticeBoardSaveResponseDto.getId()))
                .body(noticeBoardSaveResponseDto);
    }

    @GetMapping
    public ResponseEntity<NoticeBoardsResponseDto> findAllNoticeBoard(
            @RequestParam("page") Integer page,
            @RequestParam("count") int count
    ) {
        return ResponseEntity.ok(noticeBoardService.findAllNoticeBoard(page, count));
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeBoardReadResponseDto> findNoticeBoard(@PathVariable("noticeId") Long noticeId) {
        return ResponseEntity.ok(noticeBoardService.findNoticeBoard(noticeId));
    }

    @PutMapping("/admin/{noticeId}")
    public ResponseEntity<Void> updateNoticeBoard(
            @PathVariable("noticeId") Long noticeId,
            @RequestBody @Validated NoticeBoardUpdateRequest noticeBoardUpdateRequest,
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest
    ) {
        noticeBoardService.updateNoticeBoard(
                noticeBoardUpdateRequest.getContent(),
                noticeBoardUpdateRequest.getFix(),
                noticeBoardUpdateRequest.getTitle(),
                noticeId,
                loginStudentRequest.getId()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/{noticeId}")
    public ResponseEntity<Void> deleteNoticeBoard(
            @PathVariable("noticeId") Long noticeId,
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest
    ) {
        noticeBoardService.deleteNoticeBoard(noticeId, loginStudentRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
