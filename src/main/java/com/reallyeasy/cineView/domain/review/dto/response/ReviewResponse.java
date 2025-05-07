package com.reallyeasy.cineView.domain.review.dto.response;

import com.reallyeasy.cineView.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long reviewId;
    private Long movieId; // todo: Movie 정보 추가
    private Long reviewerId;
    private String reviewer;
    private String content;
    private Integer rating;
    private String createdAt;
    private String updatedAt;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.movieId = review.getMovieId();
        this.reviewerId = review.getUser().getId();
        this.reviewer = review.getUser().getName();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt().format(FORMATTER);
        this.updatedAt = review.getUpdatedAt().format(FORMATTER);
    }
}
