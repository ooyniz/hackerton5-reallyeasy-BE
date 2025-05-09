package com.reallyeasy.cineView.domain.movie.controller;

import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieWithReviewResponse;
import com.reallyeasy.cineView.domain.movie.service.MovieService;
import com.reallyeasy.cineView.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
@Slf4j
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public Page<MovieResponse> getMovies(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("releaseDate").descending());
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieWithReviewResponse> getMovieById(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @GetMapping("/favorite")
    public Page<MovieResponse> getFavoriteMovies(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @AuthenticationPrincipal User user
    ) {
        log.info(user.getUsername());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return movieService.getFavoriteMovies(user.getId(), pageable);
    }

}
