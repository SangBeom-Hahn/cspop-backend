package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.PageInfo;
import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.InvalidAuthorException;
import com.kyonggi.cspop.exception.NoSuchNoticeException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.NoticeBoardRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.comment.CommentResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardListResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardReadResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardSaveResponseDto;
import com.kyonggi.cspop.service.dto.noticeboard.NoticeBoardsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.kyonggi.cspop.utils.validator.CspopConstant.START_VIEW_COUNT;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;
    private final StudentRepository studentRepository;

    public NoticeBoardSaveResponseDto save(final String title, final String content, final Boolean fix, final Long authorId) {
        final Student author = studentRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchStudentIdException(authorId));
        final NoticeBoard noticeBoard = new NoticeBoard(content, fix, title, START_VIEW_COUNT, author);

        final Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();
        return NoticeBoardSaveResponseDto.from(saveId);
    }

    @Transactional
    public NoticeBoardReadResponseDto findNoticeBoard(final Long noticeBoardId) {
        final NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow(() -> new NoSuchNoticeException(noticeBoardId));
        noticeBoard.changeView();

        final List<CommentResponseDto> commentResponseDtos = findCommentResponseDtos(noticeBoard.getComments());
        return NoticeBoardReadResponseDto.of(noticeBoard, commentResponseDtos);
    }

    private static List<CommentResponseDto> findCommentResponseDtos(final List<Comment> comments) {
        return comments.stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoticeBoardsResponseDto findAllNoticeBoard(final Integer page, final int count) {
        final PageRequest pageRequest = PageRequest.of(page, count);
        final Page<NoticeBoard> noticeBoards = noticeBoardRepository.findAllByOrderByIdDesc(pageRequest);

        final List<NoticeBoardListResponseDto> noticeBoardListResponseDtos = noticeBoards.stream()
                .map(NoticeBoardListResponseDto::from)
                .collect(Collectors.toList());

        return NoticeBoardsResponseDto.of(noticeBoardListResponseDtos, PageInfo.from(noticeBoards));
    }

    public void updateNoticeBoard(final String content, final Boolean fix, final String title, final Long id, final Long authorId) {
        final NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NoSuchNoticeException(id));
        validateAuthor(authorId, noticeBoard);

        changeNotice(content, fix, title, noticeBoard);
    }

    private void changeNotice(final String content, final Boolean fix, final String title, final NoticeBoard noticeBoard) {
        noticeBoard.changeFix(fix);
        noticeBoard.changeContent(content);
        noticeBoard.changeTitle(title);
    }

    public void deleteNoticeBoard(final Long id, final Long authorId) {
        final NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NoSuchNoticeException(id));
        validateAuthor(authorId, noticeBoard);

        noticeBoardRepository.deleteById(id);
    }

    private void validateAuthor(final Long authorId, final NoticeBoard noticeBoard) {
        if (!noticeBoard.isAuthor(authorId)) {
            throw new InvalidAuthorException();
        }
    }
}
