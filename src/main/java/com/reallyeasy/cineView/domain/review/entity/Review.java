package com.reallyeasy.cineView.domain.review.entity;


import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.review.dto.request.ReviewCreateRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    // todo : userId, movieId
    @Column
    private Long userId;

    @Column
    private Long movieId;

    public Review(ReviewCreateRequest request, Long userId, Long movieId) {
        this.content = request.getContent();
        this.rating = request.getRating();
        this.userId = userId;
        this.movieId = movieId;
    }

}
