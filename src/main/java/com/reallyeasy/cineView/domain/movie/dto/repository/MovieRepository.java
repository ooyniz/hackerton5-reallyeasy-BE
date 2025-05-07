package com.reallyeasy.cineView.domain.movie.dto.repository;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByMovieId(Long movieId);
}
