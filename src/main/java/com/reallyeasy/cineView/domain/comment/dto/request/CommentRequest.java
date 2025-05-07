package com.reallyeasy.cineView.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
