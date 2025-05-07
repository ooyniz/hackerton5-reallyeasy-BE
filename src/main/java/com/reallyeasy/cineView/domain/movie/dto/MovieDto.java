package com.reallyeasy.cineView.domain.movie.dto;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDto {
    private Long id; // 이건 JSON의 movie ID
    private String original_language;
    private String original_title;
    private String overview;
//    private double popularity;
    private String poster_path;
    private LocalDate release_date;
    private String title;
//    private boolean video;
//    private double vote_average;
//    private int vote_count;

    public Movie toEntity() {
        return Movie.builder()
                .movieId(id)
                .title(title)
                .originalLanguage(original_language)
                .overview(overview)
                .posterPath(poster_path)
                .releaseDate(release_date)
                .build();
    }
}
