package com.bystrov.rent.service;

import com.bystrov.rent.domain.Review;

import java.util.List;

public interface ReviewService {

    Review findById(Long id);
    List<Review> getAll();
    Review saveReview(Review review);
    void update(Review review);
    void delete(Review review);
    void deleteById(Long id);
}
