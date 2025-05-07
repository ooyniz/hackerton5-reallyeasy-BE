package com.reallyeasy.cineView.domain.review.controller;

import com.reallyeasy.cineView.domain.review.dto.request.ReviewCreateRequest;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import com.reallyeasy.cineView.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    // todo
    private final Long userId = 1L;

    @PostMapping("/{movieId}")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long movieId, @Valid @RequestBody ReviewCreateRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request, userId, movieId));
    }
}
