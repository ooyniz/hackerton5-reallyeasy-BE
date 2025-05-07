package com.reallyeasy.cineView.domain.review.dto.response;

import com.reallyeasy.cineView.domain.review.entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponse {
    Long reviewId;
    Long movieId;
    String content;
    Integer rating;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.movieId = review.getMovieId();
        this.content = review.getContent();
        this.rating = review.getRating();
    }
}
