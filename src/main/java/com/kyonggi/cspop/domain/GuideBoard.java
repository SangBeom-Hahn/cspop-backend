package com.kyonggi.cspop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "guide_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuideBoard extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_board_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 15000)
    private String content;

    public GuideBoard(final String content) {
        this.content = content;
    }

    public void changeContent(final String content) {
        this.content = content;
    }
}
