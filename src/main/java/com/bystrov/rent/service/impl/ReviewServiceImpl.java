package com.bystrov.rent.service.impl;

import com.bystrov.rent.dao.ReviewDAO;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public Review findById(Long id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public Review saveReview(Review review) {
        return null;
    }

    @Override
    public void update(Review review) {

    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
