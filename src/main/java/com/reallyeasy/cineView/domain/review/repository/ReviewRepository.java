package com.reallyeasy.cineView.domain.review.repository;

import com.reallyeasy.cineView.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
