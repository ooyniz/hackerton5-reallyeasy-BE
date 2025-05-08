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
@AllArgsConstructor
public class ReviewResponse {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Long reviewId;
    private final Long movieId;
    private final Long tmdbId;
    private final String posterPath;
    private final Long reviewerId;
    private final String reviewer;
    private final String content;
    private final Integer rating;
    private final String createdAt;
    private final String updatedAt;

    public static ReviewResponse toDto(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getMovie().getId(),
                review.getMovie().getTmdbId(),
                review.getMovie().getPosterPath(),
                review.getUser().getId(),
                review.getUser().getUsername(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt().format(FORMATTER),
                review.getUpdatedAt().format(FORMATTER)
        );
    }
}
