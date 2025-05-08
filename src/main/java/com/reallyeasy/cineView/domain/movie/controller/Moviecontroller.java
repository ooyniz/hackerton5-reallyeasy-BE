package com.reallyeasy.cineView.domain.movie.controller;

import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieWithReviewResponse;
import com.reallyeasy.cineView.domain.movie.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class Moviecontroller {
    private final MovieService movieService;

    @GetMapping
    public Page<MovieResponse> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("releaseDate").descending());
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieWithReviewResponse> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

}
