package com.reallyeasy.cineView.domain.review.dto.response;

import com.reallyeasy.cineView.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private Long movieId;
    private String content;
    private Integer rating;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.movieId = review.getMovieId();
        this.content = review.getContent();
        this.rating = review.getRating();
    }
}
