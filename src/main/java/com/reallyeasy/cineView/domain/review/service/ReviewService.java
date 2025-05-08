package com.reallyeasy.cineView.domain.review.service;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewWithMovieResponse;
import com.reallyeasy.cineView.domain.review.entity.Review;
import com.reallyeasy.cineView.domain.review.repository.ReviewRepository;
import com.reallyeasy.cineView.domain.user.entity.User;
import com.reallyeasy.cineView.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewResponse createReview(ReviewRequest request, Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow();
        Movie move = movieRepository.findByTmdbId(movieId).orElseThrow();
        Review review = reviewRepository.save(new Review(request, user, move));
        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(ReviewRequest request, Long userId, Long movieId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        if (!review.getUser().getId().equals(userId))
            throw new IllegalArgumentException("해당 리뷰를 수정할 권한이 없습니다.");

        if (!review.getMovie().getId().equals(movieId))
            throw new IllegalArgumentException("리뷰와 영화 정보가 일치하지 않습니다.");

        review.updateContent(request.getContent());
        review.updateRating(request.getRating());

        return new ReviewResponse(review);
    }

    public void deleteReview(Long userId, Long movieId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        if (!review.getUser().getId().equals(userId))
            throw new IllegalArgumentException("해당 리뷰를 삭제할 권한이 없습니다.");

        // todo: 영화 ID 일치 여부 확인
        if (!review.getMovie().getId().equals(movieId))
            throw new IllegalArgumentException("리뷰와 영화 정보가 일치하지 않습니다.");

        review.markDeleted();
    }

    public List<ReviewResponse> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserIdAndDeletedAtIsNull(userId);
        return reviews.stream().map(ReviewResponse::new).collect(Collectors.toList());
    }

    public List<ReviewWithMovieResponse> getReviewsByMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieIdAndDeletedAtIsNull(movieId);
        return reviews.stream().map(ReviewWithMovieResponse::new).collect(Collectors.toList());
    }

    public ReviewResponse getReview(Long reviewId) {
        Review review = reviewRepository.findByIdAndDeletedAtIsNull(reviewId).orElseThrow();
        return new ReviewResponse(review);
    }
}
