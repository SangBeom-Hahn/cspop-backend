package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.comment.CommentSaveRequest;
import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.InvalidAuthorException;
import com.kyonggi.cspop.exception.NoSuchCommentException;
import com.kyonggi.cspop.exception.NoSuchNoticeException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest extends ServiceTest {

    private Student student;
    private NoticeBoard noticeBoard;
    private Comment comment;

    @BeforeEach
    void setUp() {
        student = new Student(
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
        noticeBoard = new NoticeBoard(
                "content1",
                false,
                "title1",
                1,
                student
        );
        comment = new Comment("content", student, noticeBoard);

        studentRepository.save(student);
        noticeBoardRepository.save(noticeBoard);
        commentRepository.save(comment);
    }

    @Test
    @DisplayName("회원가입하지 않은 회원이 댓글을 달면 예외가 발생한다.")
    void throwException_notExistedAuthor() {
        // given
        CommentSaveRequest commentSaveRequest =
                new CommentSaveRequest("content");
        Long invalidAuthorId = 999L;

        // then
        assertThatThrownBy(() -> commentService.save(
                commentSaveRequest.getContent(),
                invalidAuthorId,
                noticeBoard.getId()
        )).isInstanceOf(NoSuchStudentIdException.class)
                .hasMessage(String.format("가입되지 않은 학생입니다. studentId={%d}", invalidAuthorId));
    }

    @Test
    @DisplayName("존재하지 않은 공지사항에 댓글을 달면 예외가 발생한다.")
    void throwException_notExistedNotice() {
        // given
        CommentSaveRequest commentSaveRequest =
                new CommentSaveRequest("content");
        Long authorId = student.getId();
        Long invalidNoticeId = 999L;

        // then
        assertThatThrownBy(() -> commentService.save(
                commentSaveRequest.getContent(),
                authorId,
                invalidNoticeId
        )).isInstanceOf(NoSuchNoticeException.class)
                .hasMessage(String.format("존재하지 않은 공지사항입니다. id = {%d}", invalidNoticeId));
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 삭제하려고 하면 예외가 발생한다.")
    void throwException_invalidAuthor() {
        // given
        Long invalidAuthor = 999L;

        // then
        assertThatThrownBy(() -> commentService.deleteComment(comment.getId(), invalidAuthor))
                .isInstanceOf(InvalidAuthorException.class)
                .hasMessage("저자가 아니면 변경할 수 없습니다.");
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteComment() {
        // given
        CommentSaveRequest commentSaveRequest =
                new CommentSaveRequest("content");
        Long saveId = commentService.save(
                commentSaveRequest.getContent(),
                student.getId(),
                noticeBoard.getId()
        ).getId();

        // when
        commentService.deleteComment(saveId, student.getId());

        // then
        assertThat(commentRepository.findById(saveId))
                .isEmpty();
    }

    @Test
    @DisplayName("댓글을 저장하고 조회한다.")
    void saveCommentAndFind() {
        // given
        CommentSaveRequest commentSaveRequest =
                new CommentSaveRequest("content");
        Long saveId = commentService.save(
                commentSaveRequest.getContent(),
                student.getId(),
                noticeBoard.getId()
        ).getId();

        // when
        Comment findComment = commentRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(commentSaveRequest.getContent())
                .isEqualTo(findComment.getContent());
    }
}