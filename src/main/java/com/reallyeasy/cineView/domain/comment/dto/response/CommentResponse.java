package com.reallyeasy.cineView.domain.comment.dto.response;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long commentId;
    private Long postId;
    private Long userId;
    private String content;
    private String createdAt;
    private String updatedAt;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPostId();
        this.userId = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt().format(FORMATTER);
        this.updatedAt = comment.getUpdatedAt().format(FORMATTER);
    }
}
