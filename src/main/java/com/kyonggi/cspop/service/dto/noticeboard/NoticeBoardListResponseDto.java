package com.kyonggi.cspop.service.dto.noticeboard;

import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardListResponseDto {

    private String title;

    private Boolean fix;

    private Integer views;

    private LocalDateTime writeDate;

    private String authorLoginId;

    public static NoticeBoardListResponseDto from(NoticeBoard noticeBoard) {
        return new NoticeBoardListResponseDto(
                noticeBoard.getTitle(),
                noticeBoard.getFix(),
                noticeBoard.getViews(),
                noticeBoard.getWriteDate(),
                noticeBoard.getAuthorLoginId()
        );
    }
}
