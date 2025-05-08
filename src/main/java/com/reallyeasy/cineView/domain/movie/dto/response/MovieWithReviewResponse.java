package com.reallyeasy.cineView.domain.movie.dto.response;

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
    private List<ReviewResponse> reviews;
}
