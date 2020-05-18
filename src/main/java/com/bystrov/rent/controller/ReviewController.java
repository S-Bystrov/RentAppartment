package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.ReviewDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/advertisement/{idAdvert}/add-review")
    public String getNewReviewPage(@PathVariable("idAdvert") Long idAdvert, Model model){
        ReviewDTO reviewDTO = new ReviewDTO();
        model.addAttribute("reviewDTO", reviewDTO);
        model.addAttribute("idAdvertisement", idAdvert);
        return "new_review";
    }

    @PostMapping("/advertisement/{idAdvert}/add-review")
    public String addNewReview(@PathVariable("idAdvert") Long idAdvert,
                               @AuthenticationPrincipal User authenticalUser,
                               ReviewDTO reviewDTO){
        reviewDTO.setUser(authenticalUser);
        reviewService.saveReview(reviewDTO, idAdvert, authenticalUser.getUsername());
        return "redirect:/advertisement/" + idAdvert;
    }

    @GetMapping("/advertisement/{idAdvert}/all-review")
    public String getAllReviewPage(@PathVariable("idAdvert") Long idAdvert,
                                   Model model){
        List<ReviewDTO> reviewDTOList = reviewService.findAllByIdAdvertisement(idAdvert);
        model.addAttribute("reviewDTOList", reviewDTOList);
        return "all_review";
    }
}
