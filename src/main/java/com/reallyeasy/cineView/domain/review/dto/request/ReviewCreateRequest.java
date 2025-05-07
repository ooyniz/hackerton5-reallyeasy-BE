package com.reallyeasy.cineView.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReviewCreateRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    @NotBlank(message = "별점은 필수입니다.")
    int rating;
}
