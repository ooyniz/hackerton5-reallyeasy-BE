package com.reallyeasy.cineView.domain.review.entity;


import com.reallyeasy.cineView.domain.BaseTimeEntity;
import com.reallyeasy.cineView.domain.review.dto.request.ReviewRequest;
import com.reallyeasy.cineView.domain.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // todo : movieId
    @Column
    private Long movieId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Movie movie;

    public Review(ReviewRequest request, User user, Long movieId) {
        this.content = request.getContent();
        this.rating = request.getRating();
        this.user = user;
        this.movieId = movieId;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateRating(int rating) {
        this.rating = rating;
    }
}
