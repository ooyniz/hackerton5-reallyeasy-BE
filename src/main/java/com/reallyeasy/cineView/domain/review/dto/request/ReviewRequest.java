package com.reallyeasy.cineView.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    @NotBlank(message = "별점은 필수입니다.")
    private Integer rating;
}
