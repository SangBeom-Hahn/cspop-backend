package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.domain.noticeboard.NoticeBoard;
import com.kyonggi.cspop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    List<NoticeBoard> findAllByAuthor(Student author);
}
