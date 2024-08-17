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

    public CommentSaveResponseDto save(String content, Long authorId, Long noticeId) {
        Student student = studentRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchStudentIdException(authorId));
        NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeId)
                .orElseThrow(() -> new NoSuchNoticeException(noticeId));

        Comment comment = new Comment(content, student, noticeBoard);
        Long saveId = commentRepository.save(comment)
                .getId();
        return CommentSaveResponseDto.from(saveId);
    }

    public void deleteComment(Long id, Long authorId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchCommentException(id));
        validateAuthor(authorId, comment);
        commentRepository.deleteById(id);
    }

    private static void validateAuthor(Long authorId, Comment comment) {
        if (!comment.isAuthor(authorId)) {
            throw new InvalidAuthorException();
        }
    }
}
