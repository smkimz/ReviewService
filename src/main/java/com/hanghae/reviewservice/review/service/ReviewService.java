package com.hanghae.reviewservice.review.service;

import com.hanghae.reviewservice.product.domain.Product;
import com.hanghae.reviewservice.product.repository.ProductRepository;
import com.hanghae.reviewservice.review.domain.Review;
import com.hanghae.reviewservice.review.dto.request.CreateReviewRequest;
import com.hanghae.reviewservice.review.dto.response.GetReviewResponse;
import com.hanghae.reviewservice.review.repository.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void createReview(long productId, CreateReviewRequest request) {
        if (!request.isValidScore()) {
            throw new IllegalArgumentException("입력되는 점수는 1점 이상, 5점 이하여야 합니다.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        reviewRepository.findByUserIdAndProductId(request.getUserId(), productId)
                .ifPresent(r -> {
                    throw new IllegalArgumentException("동일한 유저는 한 상품에 리뷰를 1개만 남길 수 있습니다.");
                });

        Review review = new Review(request.getUserId(), product, request.getScore(), request.getContent());
        reviewRepository.save(review);

        product.updateReviewStats(request.getScore(), true);
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<GetReviewResponse> getReviews(long productId, int cursor, int size) {
        Pageable pageable = PageRequest.of(cursor, size);
        List<Review> reviews = reviewRepository.findByProductIdOrderByCreatedAtDesc(productId, pageable);
        return GetReviewResponse.fromReviews(reviews);
    }
}
