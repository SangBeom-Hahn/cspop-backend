package com.kyonggi.cspop.domain.noticeboard;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "write_date", nullable = false)
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_student"))
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_board_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_notice_board"))
    private NoticeBoard noticeBoard;

    public Comment(
            String content,
            Student student,
            NoticeBoard noticeBoard
    ) {
        this.content = content;
        this.writeDate = LocalDateTime.now();
        this.student = student;
        this.noticeBoard = noticeBoard;
        this.noticeBoard.addComment(this);
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public boolean isAuthor(Long id) {
        return this.student.isSame(id);
    }

    public String getLoginId() {
        return this.student
                .getLoginId();
    }
}
