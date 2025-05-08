package com.reallyeasy.cineView.domain.movie.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "TMDB_id"),
        @Index(columnList = "title"),
        @Index(columnList = "overview"),
        @Index(columnList = "posterPath"),
        @Index(columnList = "status"),
        @Index(columnList = "release_date"),
        @Index(columnList = "original_language")
})
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB에서 자동 생성

    private Long movieId; // json에서 id값
    @Column(length = 50)
    private String title;
    @Column(length = 50)
    private String originalLanguage;
    @Column(length = 1000)
    private String overview;

    @Column(length = 255)
    private String posterPath;

    private LocalDate releaseDate;

}

