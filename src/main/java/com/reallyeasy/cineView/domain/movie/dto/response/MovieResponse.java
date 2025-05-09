package com.reallyeasy.cineView.domain.movie.dto.response;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MovieResponse {
    private Long movieId;
    private Long tmdbId;
    private String title;
    private String originalLanguage;
    private String overview;
    private String posterPath;
    private LocalDate releaseDate;
    private Double averageRating;
    private int reviewCount;

    public static MovieResponse toDto(Movie movie, double averageRating, int reviewCount) {
        return new MovieResponse(
                movie.getId(),
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getOriginalLanguage(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                averageRating,
                reviewCount
        );
    }
}
