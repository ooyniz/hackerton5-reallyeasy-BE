package com.reallyeasy.cineView.domain.favoriteMovie.entity;

import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "movie_favorites")
public class FavoriteMovie extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;
    // todo
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public FavoriteMovie(Long movieId, User user) {
        this.movieId = movieId;
        this.user = user;
    }

//    public FavoriteMovie(Movie movie, User user) {
//        this.movie = movie;
//        this.user = user;
//    }
}
