package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ReviewDTO;
import com.bystrov.rent.domain.Review;

import java.util.List;

public interface ReviewService {

    ReviewDTO findById(Long id);
    List<ReviewDTO> findAllByIdAdvertisement(Long idAdvertisement);
    ReviewDTO saveReview(ReviewDTO reviewDTO, Long idAdvertisement, String username);
    void update(ReviewDTO reviewDTO);
    void delete(Review review);
    void deleteById(Long id);
}
