package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.ReservationService;
import com.bystrov.rent.validator.ReservationValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationValidator validator;

    public ReservationController(ReservationService reservationService,
                                 ReservationValidator validator) {
        this.reservationService = reservationService;
        this.validator = validator;
    }




    @GetMapping("/profile/reservation")
    public String getUserReservationPage(Model model,
                                         @AuthenticationPrincipal User authenticalUser){
        model.addAttribute("reservationList", reservationService.getAllByUserId(authenticalUser.getId()));
        return "user_reservation";
    }
}
