package com.reallyeasy.cineView.domain.comment.dto.response;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentForUserResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long commentId;
    private Long postId;
    private Long userId;
    private String content;

//    todo
//    private PostInfo post;
    private String postTitle;
    private String createdAt;
    private String updatedAt;

    public CommentForUserResponse(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPostId(); // todo
//        this.post = new PostInfo(comment.getPost());
        this.userId = comment.getUser().getId();
        this.createdAt = comment.getCreatedAt().format(FORMATTER);
        this.updatedAt = comment.getUpdatedAt().format(FORMATTER);
    }

    // todo
//    @Getter
//    public static class PostInfo {
//        private Long postId;
//        private String postTitle;
//
//        public PostInfo(Post post) {
//            this.postId = post.getId();
//            this.postTitle = post.getTitle();
//        }
//    }
}
