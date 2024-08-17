package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.noticeboard.Comment;
import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNoticeBoard(NoticeBoard noticeBoard);
}
