package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.ReviewDTO;
import com.bystrov.rent.DTO.parser.ReviewDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.ReviewDAO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;
    private final AdvertisementDAO advertisementDAO;
    private final UserDAO userDAO;
    private final ReviewDTOParser reviewDTOParser;

    public ReviewServiceImpl(ReviewDAO reviewDAO, UserDAO userDAO,
                             AdvertisementDAO advertisementDAO, ReviewDTOParser reviewDTOParser) {
        this.reviewDAO = reviewDAO;
        this.reviewDTOParser = reviewDTOParser;
        this.advertisementDAO = advertisementDAO;
        this.userDAO = userDAO;
    }

    @Override
    public ReviewDTO findById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public List<ReviewDTO> findAllByIdAdvertisement(Long idAdvertisement) {
        List<Review> reviewList = reviewDAO.findAllByIdAdvertisement(idAdvertisement);
        if(reviewList == null){
            return null;
        }
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewDTOList.add(reviewDTOParser.createReviewDTOFromDomain(review));
        }
        return reviewDTOList;
    }

    @Transactional
    @Override
    public ReviewDTO saveReview(ReviewDTO reviewDTO, Long idAdvertisement, String username) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        User user = userDAO.findByUsername(username);
        Review review = reviewDTOParser.createReviewDomainFromDTO(reviewDTO);
        review.setAdvertisement(advertisement);
        review.setUser(user);
        review.setData(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        reviewDAO.save(review);
        return reviewDTO;
    }

    @Override
    public void update(ReviewDTO reviewDTO) {

    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
