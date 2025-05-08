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
    private String original_language;
    private String overview;
    private String poster_path;
    private LocalDate release_date;

    public static MovieResponse from(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getOriginalLanguage(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate()
        );
    }
}
