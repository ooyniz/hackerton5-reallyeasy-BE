package com.reallyeasy.cineView.domain.comment.dto.response;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentForUserResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Long commentId;
    private final Long postId;
    private final Long userId;
    private final String content;
    private final String postTitle;
    private final String postContent;
    private final String createdAt;
    private final String updatedAt;

    public CommentForUserResponse(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.postContent = comment.getPost().getContent();
        this.userId = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt().format(FORMATTER);
        this.updatedAt = comment.getUpdatedAt().format(FORMATTER);
    }

    public static CommentForUserResponse toDto(Comment comment) {
        return new CommentForUserResponse(comment);
    }
}
