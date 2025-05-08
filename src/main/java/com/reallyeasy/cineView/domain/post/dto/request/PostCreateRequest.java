package com.reallyeasy.cineView.domain.post.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.entity.Post;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostCreateRequest {

    @NotNull(message = "카테고리는 필수입니다.")
    private Category category;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull
    private Long userId;

    public Post toEntity(User user) {
        return Post.builder()
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
