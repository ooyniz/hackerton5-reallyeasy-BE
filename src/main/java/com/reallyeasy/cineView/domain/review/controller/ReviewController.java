package com.reallyeasy.cineView.domain.review.controller;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.service.ReviewService;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{movieId}")
    public ResponseEntity<ReviewResponse> createReview(
            @PathVariable("movieId") Long movieId,
            @Valid @RequestBody ReviewRequest request,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        return ResponseEntity.ok(reviewService.createReview(request, user.getId(), movieId));
    }


    @PatchMapping("/{movieId}/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long movieId, @PathVariable Long reviewId, @RequestBody ReviewRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(reviewService.updateReview(request, user.getId(), movieId, reviewId));
    }

    @DeleteMapping("/{movieId}/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        reviewService.deleteReview(user.getId(), movieId, reviewId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(user.getId()));
    }

}
