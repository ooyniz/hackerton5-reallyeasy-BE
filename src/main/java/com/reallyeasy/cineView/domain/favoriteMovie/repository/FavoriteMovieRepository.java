package com.reallyeasy.cineView.domain.favoriteMovie.repository;

import com.reallyeasy.cineView.domain.favoriteMovie.entity.FavoriteMovie;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Long> {
    Optional<FavoriteMovie> findByMovieAndUser(Movie movie, User user);

    @Query("select f.movie from FavoriteMovie f where f.user.id = :userId")
    Page<Movie> findMoviesByUserId(@Param("userId") Long userId, Pageable pageable);
}
