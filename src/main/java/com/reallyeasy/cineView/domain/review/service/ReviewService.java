package com.reallyeasy.cineView.domain.review.service;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewCreateRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.entity.Review;
import com.reallyeasy.cineView.domain.review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewCreateRequest request, Long userId, Long movieId) {
        Review review = reviewRepository.save(new Review(request, userId, movieId));
        return new ReviewResponse(review);
    }
}
