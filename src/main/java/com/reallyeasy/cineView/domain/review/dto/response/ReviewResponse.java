package com.reallyeasy.cineView.domain.review.dto.response;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReviewResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Long reviewId;
    private final Long movieId; // todo: Movie 정보 추가
    private final Long reviewerId;
    private final String reviewer;
    private final String content;
    private final Integer rating;
    private final String createdAt;
    private final String updatedAt;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.movieId = review.getMovie().getId();
        this.reviewerId = review.getUser().getId();
        this.reviewer = review.getUser().getName();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt().format(FORMATTER);
        this.updatedAt = review.getUpdatedAt().format(FORMATTER);
    }
}
