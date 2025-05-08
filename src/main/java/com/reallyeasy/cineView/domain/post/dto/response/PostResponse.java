package com.reallyeasy.cineView.domain.post.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Long id;

    private String categoryName;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

    private Long userId;

    private String authorName;

    private String createdAt;

    private String updatedAt;

    public static PostResponse toDto(Post post) {
        return new PostResponse(
                post.getId(),
                post.getCategory().getDisplayName(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getCreatedAt().format(FORMATTER),
                post.getUpdatedAt().format(FORMATTER)
        );
    }
}
