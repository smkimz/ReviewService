package com.hanghae.reviewservice.review.domain;

import com.hanghae.reviewservice.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private float score;
    private String content;
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Review(long userId, Product product, float score, String content) {
        this.userId = userId;
        this.product = product;
        this.score = score;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}

