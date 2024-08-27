package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.InvalidAuthorException;
import com.kyonggi.cspop.exception.NoSuchCommentException;
import com.kyonggi.cspop.exception.NoSuchNoticeException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.CommentRepository;
import com.kyonggi.cspop.repository.NoticeBoardRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.comment.CommentSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final StudentRepository studentRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    public CommentSaveResponseDto save(final String content, final Long authorId, final Long noticeId) {
        final Student student = studentRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchStudentIdException(authorId));
        final NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeId)
                .orElseThrow(() -> new NoSuchNoticeException(noticeId));

        final Comment comment = new Comment(content, student, noticeBoard);
        final Long saveId = commentRepository.save(comment)
                .getId();
        return CommentSaveResponseDto.from(saveId);
    }

    public void deleteComment(final Long id, final Long authorId) {
        final Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchCommentException(id));
        validateAuthor(authorId, comment);
        commentRepository.deleteById(id);
    }

    private static void validateAuthor(final Long authorId, final Comment comment) {
        if (isNotAuthor(authorId, comment)) {
            throw new InvalidAuthorException();
        }
    }

    private static boolean isNotAuthor(final Long authorId, final Comment comment) {
        return !comment.isAuthor(authorId);
    }
}
