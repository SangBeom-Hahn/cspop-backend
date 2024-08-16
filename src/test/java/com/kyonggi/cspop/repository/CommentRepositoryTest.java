package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest extends RepositoryTest {

    private Student student;
    private NoticeBoard noticeBoard;

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
                "content",
                false,
                "title",
                1,
                student
        );
        studentRepository.save(student);
        noticeBoardRepository.save(noticeBoard);
    }

    @Test
    @DisplayName("댓글을 조회하고 찾는다.")
    void saveCommentAndFindById() {
        // given
        Comment comment = createComment();
        Long saveId = commentRepository.save(comment)
                .getId();

        // when
        Comment findComment = commentRepository.findById(saveId)
                .orElseThrow();
        Student findStudent = findComment.getStudent();
        NoticeBoard findNoticeBoard = findComment.getNoticeBoard();

        // then
        assertAll(
                () -> assertThat(findComment).isNotNull(),
                () -> assertThat(findComment.getId()).isEqualTo(saveId),
                () -> assertThat(findStudent.getId()).isEqualTo(student.getId()),
                () -> assertThat(findNoticeBoard.getId()).isEqualTo(findNoticeBoard.getId())
        );
    }

    @Test
    @DisplayName("공지사항으로 댓글을 조회한다.")
    void findAllByNoticeBoardId() {
        // given
        Comment comment1 = createComment();
        Comment comment2 = createComment();
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<Comment> comments = commentRepository.findAllByNoticeBoard(noticeBoard);

        // then
        assertAll(
                () -> assertThat(comments).hasSize(2),
                () -> assertThat(comments)
                        .extracting("id")
                        .containsExactly(comment1.getId(), comment2.getId())
        );
    }

    @Test
    @DisplayName("내용을 수정한다.")
    void changeContent() {
        // given
        Comment comment = createComment();
        String changeContent = "changeContent";
        Long saveId = commentRepository.save(comment)
                .getId();

        // when
        comment.changeContent(changeContent);
        Comment findComment = commentRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findComment.getContent())
                .isEqualTo(changeContent);
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteById() {
        // given
        Comment comment = createComment();
        Long saveId = commentRepository.save(comment)
                .getId();

        // when
        commentRepository.deleteById(saveId);

        // then
        assertThat(commentRepository.findById(saveId))
                .isEmpty();
    }

    private Comment createComment() {
        return new Comment(
                "content",
                student,
                noticeBoard
        );
    }
}