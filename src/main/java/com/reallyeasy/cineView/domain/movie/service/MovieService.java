package com.reallyeasy.cineView.domain.movie.service;

import com.reallyeasy.cineView.domain.favoriteMovie.repository.FavoriteMovieRepository;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieWithReviewResponse;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.entity.Review;
import com.reallyeasy.cineView.domain.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteMovieRepository favoriteMovieRepository;

    public Page<MovieResponse> getMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);

        return movies.map(movie -> {
            List<Review> reviews = reviewRepository.findByMovieId(movie.getId());

            double avgRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            int reviewCount = reviews.size();

            return MovieResponse.toDto(movie, avgRating, reviewCount);
        });
    }

    public MovieWithReviewResponse getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("영화를 찾을 수 없습니다."));
        List<Review> reviewEntities = movie.getReviews();
        List<ReviewResponse> reviews = reviewEntities.stream()
                .map(ReviewResponse::toDto)
                .toList();

        double averageRating = reviewEntities.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        int reviewCount = reviews.size();

        return MovieWithReviewResponse.toDto(movie, reviews, averageRating, reviewCount);
    }

    public Page<MovieResponse> getFavoriteMovies(Long userId, Pageable pageable) {
        Page<Movie> movies = favoriteMovieRepository.findMoviesByUserId(userId, pageable);
        return movies.map(movie -> {
            List<Review> reviews = reviewRepository.findByMovieId(movie.getId());

            double averageRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);
            int reviewCount = reviews.size();

            return MovieResponse.toDto(movie, averageRating, reviewCount);
        });
    }
}
