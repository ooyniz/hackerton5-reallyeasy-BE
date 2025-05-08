package com.reallyeasy.cineView.domain.movie.entity;


import com.reallyeasy.cineView.domain.movie.dto.request.MovieRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.time.LocalDate;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "tmdb_id"),
        @Index(columnList = "title"),
        @Index(columnList = "posterPath"),
        @Index(columnList = "release_date"),
        @Index(columnList = "original_language")
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB에서 자동 생성

    @Column
    private Long tmdbId; // json에서 id값

    @Column
    private String title;

    @Column
    private String originalLanguage;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column
    private String posterPath;

    @Column
    private LocalDate releaseDate;

    @Version
    private Long version;

    @Builder
    public Movie(Long tmdbId, String title, String originalLanguage, String overview, String posterPath, LocalDate releaseDate) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    public void updateFromRequest(MovieRequest dto) {
        this.title = dto.getTitle();
        this.tmdbId = dto.getId();
        this.originalLanguage = dto.getOriginal_language();
        this.overview = dto.getOverview();
        this.posterPath = dto.getPoster_path();
        this.releaseDate = dto.getRelease_date();
    }
}

