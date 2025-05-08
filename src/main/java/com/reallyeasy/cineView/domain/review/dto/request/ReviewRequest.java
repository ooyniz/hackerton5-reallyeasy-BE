package com.reallyeasy.cineView.domain.review.dto.request;

import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.review.entity.Review;
import com.reallyeasy.cineView.domain.user.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ReviewRequest {
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    @NotBlank(message = "별점은 필수입니다.")
    @Min(1)
    @Max(5)
    private Integer rating;

    public Review toEntity(User user, Movie movie) {
        return Review.builder()
                .content(this.content)
                .rating(this.rating)
                .user(user)
                .movie(movie)
                .build();
    }
}
