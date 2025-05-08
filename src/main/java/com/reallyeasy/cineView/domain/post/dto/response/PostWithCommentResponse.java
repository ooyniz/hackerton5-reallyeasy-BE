package com.reallyeasy.cineView.domain.post.dto.response;

import com.reallyeasy.cineView.domain.comment.dto.response.CommentResponse;
import com.reallyeasy.cineView.domain.post.entity.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PostWithCommentResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Long postId;
    private final String title;
    private final String content;
    private final String authorName;
    private final String createdAt;
    private final String updatedAt;
    private final List<CommentResponse> comments;

    public PostWithCommentResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.authorName = post.getUser().getName(); // Post → User → name
        this.createdAt = post.getCreatedAt().format(FORMATTER);
        this.updatedAt = post.getUpdatedAt().format(FORMATTER);

        this.comments = post.getComments().stream()
                .map(CommentResponse::new)
                .toList();
    }
}
