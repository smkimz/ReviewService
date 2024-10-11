package com.hanghae.reviewservice.review.controller;

import com.hanghae.reviewservice.review.dto.request.CreateReviewRequest;
import com.hanghae.reviewservice.review.dto.response.GetReviewResponse;
import com.hanghae.reviewservice.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<GetReviewResponse>> getReviews(
            @PathVariable long productId,
            @RequestParam(defaultValue = "0") int cursor,
            @RequestParam(defaultValue = "10") int size) {
        List<GetReviewResponse> reviews = reviewService.getReviews(productId, cursor, size);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Void> createReview(
            @PathVariable long productId,
            @RequestBody CreateReviewRequest request) {
        reviewService.createReview(productId, request);
        return ResponseEntity.ok().build();
    }
}
