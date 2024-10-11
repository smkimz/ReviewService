package com.hanghae.reviewservice.review.repository;

import com.hanghae.reviewservice.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductIdOrderByCreatedAtDesc(long productId, Pageable pageable);

    Optional<Review> findByUserIdAndProductId(long userId, long productId);
}