package com.reallyeasy.cineView.domain.comment.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    // todo
    private Long postId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void updateContent(String content) {
        this.content = content;
    }
}
