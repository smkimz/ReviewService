package com.hanghae.reviewservice.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {

    private long userId;
    private float score;
    private String content;

    public boolean isValidScore() {
        return score >= 1 && score <= 5;
    }
}

