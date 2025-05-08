package com.reallyeasy.cineView.domain.comment.dto.response;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Long commentId;
    private final Long postId;
    private final Long userId;
    private final String content;
    private final String createdAt;
    private final String updatedAt;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt().format(FORMATTER);
        this.updatedAt = comment.getUpdatedAt().format(FORMATTER);
    }

    public static CommentResponse toDto(Comment comment) {
        return new CommentResponse(comment);
    }
}
