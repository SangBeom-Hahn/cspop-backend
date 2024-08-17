package com.kyonggi.cspop.domain.noticeboard;

import com.kyonggi.cspop.domain.student.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoticeBoardTest {

    @Test
    @DisplayName("내용물을 수정한다.")
    void changeContent() {
        // given
        NoticeBoard noticeBoard = createNoticeBoard();
        String changeContent = "changeContent";

        // when
        noticeBoard.changeContent(changeContent);

        // then
        assertThat(noticeBoard.getContent())
                .isEqualTo(changeContent);
    }

    @Test
    @DisplayName("제목을 수정한다.")
    void changeTitle() {
        // given
        NoticeBoard noticeBoard = createNoticeBoard();
        String changeTitle = "changeTitle";

        // when
        noticeBoard.changeTitle(changeTitle);

        // then
        assertThat(noticeBoard.getTitle())
                .isEqualTo(changeTitle);
    }
    
    @Test
    @DisplayName("고정 여부를 수정한다.")
    void changeFix() {
        // given
        NoticeBoard noticeBoard = createNoticeBoard();
        Boolean changeFix = true;

        // when
        noticeBoard.changeFix(changeFix);
      
        // then
        assertThat(noticeBoard.getFix())
                .isEqualTo(changeFix);
    }

    @Test
    @DisplayName("조회수를 1 증가한다.")
    void changeViews() {
        // given
        NoticeBoard noticeBoard = createNoticeBoard();
        Integer expectedViews = noticeBoard.getViews() + 1;

        // when
        noticeBoard.changeView();

        // then
        assertThat(noticeBoard.getViews())
                .isEqualTo(expectedViews);
    }

    @Test
    @DisplayName("작성자가 아니면 false")
    void NoticeBoardTest() {
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
        Long notAuthorId = 999L;

        // then
        assertThat(noticeBoard.isAuthor(notAuthorId))
                .isFalse();
    }

    @Test
    @DisplayName("공지사항을 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> createNoticeBoard());
    }

    @Test
    @DisplayName("댓글을 추가한다.")
    void addComment() {
        // given
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
        Comment comment1 = new Comment(
                "content",
                author,
                noticeBoard
        );
        Comment comment2 = new Comment(
                "content",
                author,
                noticeBoard
        );
        List<Comment> expected = List.of(comment1, comment2);

        // then
        assertThat(noticeBoard.getComments()).usingRecursiveAssertion()
                .isEqualTo(expected);
    }

    private NoticeBoard createNoticeBoard() {
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

        return new NoticeBoard(
                "content",
                false,
                "title",
                1,
                author
        );
    }
}