package com.kyonggi.cspop.controller;

import com.kyonggi.cspop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.cspop.controller.dto.comment.CommentSaveRequest;
import com.kyonggi.cspop.controller.dto.student.LoginStudentRequest;
import com.kyonggi.cspop.service.CommentService;
import com.kyonggi.cspop.service.dto.comment.CommentSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{noticeBoardId}")
    public ResponseEntity<CommentSaveResponseDto> create(
            @PathVariable("noticeBoardId") Long noticeBoardId,
            @RequestBody CommentSaveRequest commentSaveRequest,
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest
    ) {
        final CommentSaveResponseDto commentSaveResponseDto =
                commentService.save(commentSaveRequest.getContent(), loginStudentRequest.getId(), noticeBoardId);
        return ResponseEntity
                .created(URI.create("/api/comments/" + commentSaveResponseDto.getId()))
                .body(commentSaveResponseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("commentId") Long commentId,
            @AuthenticationPrincipal LoginStudentRequest loginStudentRequest
    ) {
        commentService.deleteComment(commentId, loginStudentRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
