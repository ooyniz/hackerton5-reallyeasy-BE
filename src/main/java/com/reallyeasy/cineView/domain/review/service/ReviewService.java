package com.reallyeasy.cineView.domain.review.service;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
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

    public ReviewResponse createReview(ReviewRequest request, Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElseThrow();
        Review review = reviewRepository.save(new Review(request, user, movieId));
        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(ReviewRequest request, Long userId, Long movieId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        if (!review.getUser().equals(user))
            throw new IllegalArgumentException("해당 리뷰를 수정할 권한이 없습니다.");

        // todo: 영화 ID 일치 여부 확인
        if (!review.getMovieId().equals(movieId))
            throw new IllegalArgumentException("리뷰와 영화 정보가 일치하지 않습니다.");

        review.updateContent(request.getContent());
        review.updateRating(request.getRating());

        return new ReviewResponse(review);
    }

    public void deleteReview(Long userId, Long movieId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        if (!review.getUser().equals(user))
            throw new IllegalArgumentException("해당 리뷰를 수정할 권한이 없습니다.");

        // todo: 영화 ID 일치 여부 확인
        if (!review.getMovieId().equals(movieId))
            throw new IllegalArgumentException("리뷰와 영화 정보가 일치하지 않습니다.");

        reviewRepository.delete(review);
    }

    public List<ReviewResponse> getReviewsByUser(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        // todo user
        return reviews.stream().map(ReviewResponse::new).collect(Collectors.toList());
    }

    public List<ReviewResponse> getReviewsByMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieId(movieId);
        // todo movie
        return reviews.stream().map(ReviewResponse::new).collect(Collectors.toList());
    }
}
