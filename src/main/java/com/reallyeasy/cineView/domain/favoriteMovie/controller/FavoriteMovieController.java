package com.reallyeasy.cineView.domain.favoriteMovie.controller;

import com.reallyeasy.cineView.domain.favoriteMovie.service.FavoriteMovieService;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite-movies")
@RequiredArgsConstructor
public class FavoriteMovieController {
    private final FavoriteMovieService favoriteMovieService;

    @PostMapping("/{movieId}")
    public ResponseEntity<?> toggleFavorite(@PathVariable Long movieId, @AuthenticationPrincipal User user) {
        favoriteMovieService.toggleFavorite(movieId, user.getId());
        return ResponseEntity.ok().build();
    }

}
