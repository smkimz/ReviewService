package com.hanghae.reviewservice.review.dto.response;

import com.hanghae.reviewservice.review.domain.Review;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GetReviewResponse {

    private long id;
    private long userId;
    private float score;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    public static List<GetReviewResponse> fromReviews(List<Review> reviews) {
        return reviews.stream().map(review -> {
            GetReviewResponse response = new GetReviewResponse();
            response.setId(review.getId());
            response.setUserId(review.getUserId());
            response.setScore(review.getScore());
            response.setContent(review.getContent());
            response.setImageUrl(review.getImageUrl());
            response.setCreatedAt(review.getCreatedAt());
            return response;
        }).collect(Collectors.toList());
    }
}

