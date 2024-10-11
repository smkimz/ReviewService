package com.hanghae.reviewservice.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long reviewCount;
    private float score;

    public void updateReviewStats(float newScore, boolean isNewReview) {
        if (!isNewReview)
            return;
        this.reviewCount++;
        this.score = (this.score * (this.reviewCount - 1) + newScore) / this.reviewCount;
    }
}
