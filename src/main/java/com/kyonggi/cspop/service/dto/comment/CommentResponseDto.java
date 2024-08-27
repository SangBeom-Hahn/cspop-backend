package com.kyonggi.cspop.service.dto.comment;

import com.kyonggi.cspop.domain.noticeboard.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

    private String content;

    private LocalDateTime writeDate;

    private String loginId;

    public static CommentResponseDto from(final Comment comment) {
        return new CommentResponseDto(
                comment.getContent(),
                comment.getWriteDate(),
                comment.getLoginId()
        );
    }
}
