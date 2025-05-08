package com.reallyeasy.cineView.domain.comment.dto.request;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import com.reallyeasy.cineView.domain.post.entity.Post;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    // todo toEntity 함수
    public Comment toEntity(Post post, User user) {
        return Comment.builder()
                .content(this.content)
                .post(post)
                .user(user)
                .build();
    }
}