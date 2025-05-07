package com.reallyeasy.cineView.domain.favoriteMovie.controller;

import com.reallyeasy.cineView.domain.favoriteMovie.service.FavoriteMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favoriteMovies")
@RequiredArgsConstructor
public class FavoriteMovieController {
    // todo
    private final Long userId = 1L;
    private final FavoriteMovieService favoriteMovieService;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long movieId) {
        favoriteMovieService.toggleFavorite(movieId, userId);
        return ResponseEntity.ok().build();
    }

    // todo
    @GetMapping
    public ResponseEntity<?> getFavoriteMovies() {
        favoriteMovieService.getFavoriteMovies(userId);
        return ResponseEntity.ok().build();
    }
}
