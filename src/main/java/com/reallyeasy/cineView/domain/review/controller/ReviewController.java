package com.reallyeasy.cineView.domain.review.controller;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    // todo
    private final Long userId = 1L;

    @PostMapping("/{movieId}")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long movieId, @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request, userId, movieId));
    }

    @PatchMapping("/{movieId}/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long movieId, @PathVariable Long reviewId, @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(request, userId, movieId, reviewId));
    }

    @DeleteMapping("/{movieId}/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId) {
        reviewService.deleteReview(userId, movieId, reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }
}
