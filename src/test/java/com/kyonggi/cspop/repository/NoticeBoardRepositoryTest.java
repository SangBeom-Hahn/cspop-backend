package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NoticeBoardRepositoryTest extends RepositoryTest {

    private Student student;
    private NoticeBoard noticeBoard;
    private NoticeBoard noticeBoard1;

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
        noticeBoard1 = new NoticeBoard(
                "content",
                false,
                "title",
                1,
                student
        );

        studentRepository.save(student);
    }

    @Test
    @DisplayName("공지사항을 저장하고 pk로 찾는다.")
    void saveNoticeBoardAndFindById() {
        // when
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        Student author = saveNoticeBoard.getAuthor();

        // then
        assertAll(
                () -> assertThat(saveNoticeBoard.getId()).isNotNull(),
                () -> assertThat(saveNoticeBoard).isEqualTo(noticeBoard),
                () -> assertThat(author.getId()).isEqualTo(student.getId())
        );
    }

    @Test
    @DisplayName("작성자 id로 공지사항을 조회한다.")
    void findAllByStudentId() {
        // given
        noticeBoardRepository.save(noticeBoard);
        noticeBoardRepository.save(noticeBoard1);

        // when
        List<NoticeBoard> noticeBoards = noticeBoardRepository.findAllByAuthor(student);
        List<NoticeBoard> expected = List.of(noticeBoard, noticeBoard1);

        // then
        assertAll(
                () -> assertThat(noticeBoards).hasSize(2),
                () -> assertThat(noticeBoards)
                        .usingRecursiveAssertion()
                        .isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("pk로 공지사항을 찾는다.")
    void findById() {
        // given
        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();

        // when
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findNoticeBoard).usingRecursiveComparison()
                .isEqualTo(noticeBoard);
    }

    @Test
    @DisplayName("공지 내용을 수정한다.")
    void updateContent() {
        // given
        String changeContent = "changeContent";
        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();

        // when
        noticeBoard.changeContent(changeContent);
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findNoticeBoard.getContent())
                .isEqualTo(changeContent);
    }

    @Test
    @DisplayName("고정 여부를 수정한다.")
    void updateFix() {
        // given
        Boolean changeFix = true;
        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();

        // when
        noticeBoard.changeFix(changeFix);
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findNoticeBoard.getFix())
                .isEqualTo(changeFix);
    }

    @Test
    @DisplayName("조회수를 1증가한다.")
    void updateView() {
        // given
        int expected = noticeBoard.getViews() + 1;
        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();

        // when
        noticeBoard.changeView();
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(saveId)
                .orElseThrow();

        // then
        assertThat(findNoticeBoard.getViews())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("공지사항을 삭제한다.")
    void deleteById() {
        // given
        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();

        // when
        noticeBoardRepository.deleteById(saveId);

        // then
        assertThat(noticeBoardRepository.findById(saveId))
                .isEmpty();
    }
}