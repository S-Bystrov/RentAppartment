package com.bystrov.rent.dao;

import com.bystrov.rent.domain.Review;

import java.util.List;

public interface ReviewDAO {

    void save(Review review);
    Review update(Review review);
    Review findById(Long id);
    void delete(Review review);
    void deleteById(Long id);
    List<Review> getAll();
}
