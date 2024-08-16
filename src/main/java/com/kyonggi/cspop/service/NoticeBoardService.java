package com.kyonggi.cspop.service;

import com.kyonggi.cspop.controller.dto.PageInfo;
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

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;
    private final StudentRepository studentRepository;

    public NoticeBoardSaveResponseDto save(String title, String content, Boolean fix, Long authorId) {
        Student author = studentRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchStudentIdException(authorId));
        NoticeBoard noticeBoard = new NoticeBoard(content, fix, title, 1, author);

        Long saveId = noticeBoardRepository.save(noticeBoard)
                .getId();
        return NoticeBoardSaveResponseDto.from(saveId);
    }

    public NoticeBoardReadResponseDto findNoticeBoard(Long noticeBoardId) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow(() -> new NoSuchNoticeException(noticeBoardId));
        noticeBoard.changeView();

        List<CommentResponseDto> commentResponseDtos = findCommentResponseDtos(noticeBoard);
        return NoticeBoardReadResponseDto.of(noticeBoard, commentResponseDtos);
    }

    private static List<CommentResponseDto> findCommentResponseDtos(NoticeBoard noticeBoard) {
        return noticeBoard.getComments()
                .stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }

    public NoticeBoardsResponseDto findAllNoticeBoard(Integer page, int count) {
        PageRequest pageRequest = PageRequest.of(page, count);
        Page<NoticeBoard> noticeBoards = noticeBoardRepository.findAllByOrderByIdDesc(pageRequest);

        List<NoticeBoardListResponseDto> noticeBoardListResponseDtos = noticeBoards.stream()
                .map(NoticeBoardListResponseDto::from)
                .collect(Collectors.toList());

        return NoticeBoardsResponseDto.of(noticeBoardListResponseDtos, PageInfo.from(noticeBoards));
    }

    public void updateNoticeBoard(String content, Boolean fix, String title, Long id, Long authorId) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NoSuchNoticeException(id));
        validateAuthor(authorId, noticeBoard);

        changeNotice(content, fix, title, noticeBoard);
    }

    private void changeNotice(String content, Boolean fix, String title, NoticeBoard noticeBoard) {
        noticeBoard.changeFix(fix);
        noticeBoard.changeContent(content);
        noticeBoard.changeTitle(title);
    }

    public void deleteNoticeBoard(Long id, Long authorId) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NoSuchNoticeException(id));
        validateAuthor(authorId, noticeBoard);

        noticeBoardRepository.deleteById(id);
    }

    private void validateAuthor(Long authorId, NoticeBoard noticeBoard) {
        if (!noticeBoard.isAuthor(authorId)) {
            throw new InvalidAuthorException();
        }
    }
}
