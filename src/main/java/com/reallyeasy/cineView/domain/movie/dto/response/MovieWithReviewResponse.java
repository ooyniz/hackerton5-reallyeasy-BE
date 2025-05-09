package com.reallyeasy.cineView.domain.movie.dto.response;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class MovieWithReviewResponse {
    private Long movieId;
    private Long tmdbId;
    private String title;
    private String original_language;
    private String overview;
    private String poster_path;
    private LocalDate release_date;
    private double averageRating;
    private int reviewCount;
    private List<ReviewResponse> reviews;

    public static MovieWithReviewResponse toDto(Movie movie, List<ReviewResponse> reviews, double averageRating, int reviewCount) {
        return new MovieWithReviewResponse(
                movie.getId(),
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getOriginalLanguage(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                averageRating,
                reviewCount,
                reviews
        );
    }
}
