package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.PageInfo;
import com.kyonggi.cspop.controller.dto.noticeboard.NoticeBoardSaveRequest;
import com.kyonggi.cspop.controller.dto.noticeboard.NoticeBoardUpdateRequest;
import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.exception.InvalidAuthorException;
import com.kyonggi.cspop.exception.NoSuchNoticeException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.service.dto.comment.CommentResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardListResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardReadResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardSaveResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class NoticeBoardServiceTest extends ServiceTest {

    private Student student;
    private NoticeBoard noticeBoard;
    private NoticeBoard noticeBoard1;
    private NoticeBoard noticeBoard2;
    private Comment comment1;
    private Comment comment2;

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
        noticeBoard1 = new NoticeBoard(
                "content2",
                false,
                "title2",
                1,
                student
        );
        noticeBoard2 = new NoticeBoard(
                "content3",
                false,
                "title3",
                1,
                student
        );
        comment1 = new Comment(
                "content1",
                student,
                noticeBoard
        );
        comment2 = new Comment(
                "content2",
                student,
                noticeBoard
        );

        studentRepository.save(student);
        noticeBoardRepository.save(noticeBoard);
        noticeBoardRepository.save(noticeBoard1);
        noticeBoardRepository.save(noticeBoard2);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
    }

    @Test
    @DisplayName("회원가입이 안 된 관리자가 공지사항을 저장하면 예외가 발생한다.")
    void throwException_invalidStudentId() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();
        Long invalidId = 999L;

        // then
        assertThatThrownBy(() -> noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                invalidId
        )).isInstanceOf(NoSuchStudentIdException.class)
                .hasMessage(String.format("가입되지 않은 학생입니다. studentId={%d}", invalidId));
    }

    @Test
    @DisplayName("없는 공지를 조회하면 예외가 발생한다.")
    void throwException_cannotFindNotice() {
        // given
        Long invalidNoticeId = 999L;

        // then
        assertThatThrownBy(() -> noticeBoardService.findNoticeBoard(invalidNoticeId))
                .isInstanceOf(NoSuchNoticeException.class)
                .hasMessage(String.format("존재하지 않은 공지사항입니다. id = {%d}", invalidNoticeId));
    }

    @Test
    @DisplayName("게시물 작성자가 아닌 사람이 게시글을 수정하면 예외가 발생한다.")
    void throwException_invalidAuthor() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();
        Long saveId = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                student.getId()
        ).getId();
        NoticeBoardUpdateRequest noticeBoardUpdateRequest = new NoticeBoardUpdateRequest(
                "newcontent",
                true,
                "newtitle"
        );
        Long invalidAuthor = 999L;

        // then
        assertThatThrownBy(() -> noticeBoardService.updateNoticeBoard(
                noticeBoardUpdateRequest.getContent(),
                noticeBoardUpdateRequest.getFix(),
                noticeBoardUpdateRequest.getTitle(),
                saveId,
                invalidAuthor
        )).isInstanceOf(InvalidAuthorException.class)
                .hasMessage("저자가 아니면 변경할 수 없습니다.");
    }

    @Test
    @DisplayName("공지사항을 저장하고 조회한다.")
    void saveNoticeBoardAndFind() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();

        // when
        Long saveId = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                student.getId()
        ).getId();
        NoticeBoardReadResponseDto noticeBoardReadResponseDto =
                noticeBoardService.findNoticeBoard(saveId);

        // then
        assertThat(noticeBoardReadResponseDto)
                .extracting("content", "title", "authorLoginId")
                .containsExactly(
                        noticeBoardSaveRequest.getContent(),
                        noticeBoardSaveRequest.getTitle(),
                        student.getLoginId()
                );
    }

    @Test
    @DisplayName("공지사항에 등록된 댓글을 조회한다.")
    void findNoticeBoardWithComment() {
        // given
        List<CommentResponseDto> expected = List.of(
                CommentResponseDto.from(comment1),
                CommentResponseDto.from(comment2)
        );

        // when
        NoticeBoardReadResponseDto noticeBoardReadResponseDto =
                noticeBoardService.findNoticeBoard(noticeBoard.getId());

        // then
        assertThat(noticeBoardReadResponseDto.getCommentResponseDtos())
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("공지사항을 조회하면 조회수가 1 증가한다.")
    void updateView() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();
        Long saveId = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                student.getId()
        ).getId();
        Integer expected = noticeBoardRepository.findById(saveId)
                .orElseThrow()
                .getViews() + 1;

        // when
        NoticeBoardReadResponseDto noticeBoardReadResponseDto =
                noticeBoardService.findNoticeBoard(saveId);

        // then
        assertThat(noticeBoardReadResponseDto.getViews())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("공지사항 리스트에서 3개를 페이징한다. [page = 0, count = 3]")
    void findNoticeBoardPage() {
        // given
        Integer page = 0;
        int count = 3;
        List<NoticeBoardListResponseDto> noticeBoardListResponseDtos = List.of(
                NoticeBoardListResponseDto.from(noticeBoard2),
                NoticeBoardListResponseDto.from(noticeBoard1),
                NoticeBoardListResponseDto.from(noticeBoard)
        );
        PageInfo pageInfo = PageInfo.of(0, 1, 3, 3);
        NoticeBoardsResponseDto expected = NoticeBoardsResponseDto.of(noticeBoardListResponseDtos, pageInfo);

        // when
        NoticeBoardsResponseDto actual =
                noticeBoardService.findAllNoticeBoard(page, count);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("공지사항을 수정한다.")
    void updateNoticeBoard() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();
        Long saveId = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                student.getId()
        ).getId();
        NoticeBoardUpdateRequest noticeBoardUpdateRequest = new NoticeBoardUpdateRequest(
                "newcontent",
                true,
                "newtitle"
        );

        // when
        noticeBoardService.updateNoticeBoard(
                noticeBoardUpdateRequest.getContent(),
                noticeBoardUpdateRequest.getFix(),
                noticeBoardUpdateRequest.getTitle(),
                saveId,
                student.getId()
        );
        NoticeBoardReadResponseDto noticeBoardReadResponseDto =
                noticeBoardService.findNoticeBoard(saveId);

        // then
        assertThat(noticeBoardReadResponseDto)
                .extracting("title", "content")
                .containsExactly("newtitle", "newcontent");
    }

    @Test
    @DisplayName("공지사항을 삭제한다.")
    void deleteNoticeBoard() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createSaveRequest();
        Long saveId = noticeBoardService.save(
                noticeBoardSaveRequest.getTitle(),
                noticeBoardSaveRequest.getContent(),
                noticeBoardSaveRequest.getFix(),
                student.getId()
        ).getId();

        // when
        noticeBoardService.deleteNoticeBoard(saveId, student.getId());

        // then
        assertThatThrownBy(() -> noticeBoardService.findNoticeBoard(saveId))
                .isInstanceOf(NoSuchNoticeException.class)
                .hasMessage(String.format("존재하지 않은 공지사항입니다. id = {%d}", saveId));
    }

    private NoticeBoardSaveRequest createSaveRequest() {
        return new NoticeBoardSaveRequest(
                "content",
                false,
                "title"
        );
    }
}