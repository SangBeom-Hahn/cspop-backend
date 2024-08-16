package com.kyonggi.cspop.domain.noticeboard;

import com.kyonggi.cspop.domain.BaseEntity;
import com.kyonggi.cspop.domain.student.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "notice_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_board_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "fix", nullable = false)
    private Boolean fix;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "views", nullable = false)
    private Integer views;

    @Column(name = "write_date", nullable = false)
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_notice_board_student"), nullable = false)
    private Student author;

    @OneToMany(mappedBy = "noticeBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public NoticeBoard(
            String content,
            Boolean fix,
            String title,
            Integer views,
            Student author
    ) {
        this.content = content;
        this.fix = fix;
        this.title = title;
        this.views = views;
        this.writeDate = LocalDateTime.now();
        this.author = author;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeFix(Boolean fix) {
        this.fix = fix;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeView() {
        this.views += 1;
    }

    public boolean isAuthor(Long id) {
        return this.author.isSame(id);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
