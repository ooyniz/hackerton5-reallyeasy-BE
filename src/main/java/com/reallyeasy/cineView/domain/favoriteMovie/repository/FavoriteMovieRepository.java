package com.reallyeasy.cineView.domain.favoriteMovie.repository;

import com.reallyeasy.cineView.domain.favoriteMovie.entity.FavoriteMovie;
import com.reallyeasy.cineView.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
//    Optional<FavoriteMovie> findByMovieAndUser(Movie movie, User user);

    Optional<FavoriteMovie> findByMovieIdAndUserId(Long movieId, Long userId);
}
