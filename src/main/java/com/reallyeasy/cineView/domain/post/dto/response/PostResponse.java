package com.reallyeasy.cineView.domain.post.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.reallyeasy.cineView.common.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostResponse {
    private Long id;

    private Category category;

    private String categoryName;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;

    private Long userId;

    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
