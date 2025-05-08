package com.reallyeasy.cineView.domain.comment.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Builder
    public Comment(User user, Long postId, String content) {
        this.user = user;
        this.postId = postId;
        this.content = content;
    }
    public void updateContent(String content) {
        this.content = content;
    }
}
