package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.ReviewDTO;
import com.bystrov.rent.domain.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewDTOParser {

    public ReviewDTO createReviewDTOFromDomain(Review review){
        if (review == null){
            return null;
        }
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .idReview(review.getIdReview())
                .user(review.getUser())
                .review(review.getReview())
                .data(review.getData())
                .evaluation(review.getEvaluation())
                .advertisement(review.getAdvertisement())
                .build();
        return reviewDTO;
    }

    public Review createReviewDomainFromDTO(ReviewDTO reviewDTO){
        if (reviewDTO == null){
            return null;
        }
        Review review = Review.builder()
                .idReview(reviewDTO.getIdReview())
                .user(reviewDTO.getUser())
                .review(reviewDTO.getReview())
                .data(reviewDTO.getData())
                .evaluation(reviewDTO.getEvaluation())
                .advertisement(reviewDTO.getAdvertisement())
                .build();
        return review;
    }
}
