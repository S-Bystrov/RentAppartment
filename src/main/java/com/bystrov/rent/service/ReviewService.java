package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ReviewService {

    ReviewDTO findById(Long id);
    List<ReviewDTO> findAllByIdAdvertisement(Long idAdvertisement);
    Page<ReviewDTO> findPagination(Pageable pageable, Long idAdvertisement);
    ReviewDTO saveReview(ReviewDTO reviewDTO, Long idAdvertisement, String username);
    void update(ReviewDTO reviewDTO);
    void deleteById(Long id);
}
