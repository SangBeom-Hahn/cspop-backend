package com.kyonggi.cspop.service.dto.noticeboard;

import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.service.dto.comment.CommentResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardReadResponseDto {

    private String title;

    private String content;

    private Integer views;

    private String authorLoginId;

    private LocalDateTime writeDate;

    private List<CommentResponseDto> commentResponseDtos;

    public static NoticeBoardReadResponseDto of(NoticeBoard noticeBoard, List<CommentResponseDto> commentResponseDtos) {
        return new NoticeBoardReadResponseDto(
                noticeBoard.getTitle(),
                noticeBoard.getContent(),
                noticeBoard.getViews(),
                noticeBoard.getAuthorLoginId(),
                noticeBoard.getWriteDate(),
                commentResponseDtos
        );
    }
}
