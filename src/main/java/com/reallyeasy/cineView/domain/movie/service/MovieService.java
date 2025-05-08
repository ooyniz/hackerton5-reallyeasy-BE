package com.reallyeasy.cineView.domain.movie.service;

import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public Page<MovieResponse> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(MovieResponse::from);
    }
}
