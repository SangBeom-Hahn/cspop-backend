package com.kyonggi.cspop.domain.noticeboard;

import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    @DisplayName("댓글을 생성한다.")
    void construct() {
        // given
        assertDoesNotThrow(() -> createComment());
    }

    @Test
    @DisplayName("내용을 수정한다.")
    void changeContent() {
        // given
        Comment comment = createComment();
        String changeContent = "changeContent";

        // when
        comment.changeContent(changeContent);

        // then
        assertThat(comment.getContent())
                .isEqualTo(changeContent);
    }

    @Test
    @DisplayName("작성자가 아니면 false")
    void isAuthor() {
        // given
        Student author = new Student(
                1L,
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT,
                RoleType.ADMIN
        );
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "title",
                1,
                author
        );
        Comment comment = new Comment(
                "content",
                author,
                noticeBoard
        );

        Long notAuthorId = 999L;

        // then
        assertThat(comment.isAuthor(notAuthorId))
                .isFalse();
    }

    private static Comment createComment() {
        Student author = new Student(
                "201811111",
                "123",
                "111&!a",
                now().minusDays(1),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        );

        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "title",
                1,
                author
        );

        return new Comment(
                "content",
                author,
                noticeBoard
        );
    }
}