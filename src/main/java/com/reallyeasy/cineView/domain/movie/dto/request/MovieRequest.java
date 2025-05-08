package com.reallyeasy.cineView.domain.movie.dto.request;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MovieRequest {
    private Long id;
    private String title;
    private String original_language;
    private String overview;
    private String poster_path;
    private LocalDate release_date;

    public Movie toEntity() {
        return Movie.builder()
                .tmdbId(this.id)
                .title(this.title)
                .originalLanguage(this.original_language)
                .overview(this.overview)
                .posterPath(this.poster_path)
                .releaseDate(this.release_date)
                .build();
    }
}
