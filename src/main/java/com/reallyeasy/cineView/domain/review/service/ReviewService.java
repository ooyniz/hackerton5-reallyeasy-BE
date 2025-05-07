package com.reallyeasy.cineView.domain.review.service;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.entity.Review;
import com.reallyeasy.cineView.domain.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewRequest request, Long userId, Long movieId) {
        Review review = reviewRepository.save(new Review(request, userId, movieId));
        return new ReviewResponse(review);
    }

    public ReviewResponse updateReview(ReviewRequest request, Long userId, Long movieId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        // todo movie, user
        review.updateContent(request.getContent());
        review.updateRating(request.getRating());

        return new ReviewResponse(review);
    }
}
