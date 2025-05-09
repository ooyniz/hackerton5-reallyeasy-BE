package com.reallyeasy.cineView.domain.review.repository;

import com.reallyeasy.cineView.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserIdAndDeletedAtIsNull(Long userId);

    Optional<Review> findByIdAndDeletedAtIsNull(Long reviewId);

    List<Review> findByMovieId(Long movieId);
}
