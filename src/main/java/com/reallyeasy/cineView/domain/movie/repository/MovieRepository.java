package com.reallyeasy.cineView.domain.movie.repository;

import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTmdbId(Long tmdbId);

    @Query("SELECT fm.movie FROM FavoriteMovie fm WHERE fm.user.id = :userId")
    List<Movie> findFavoriteMoviesByUserId(@Param("userId") Long userId);
}
