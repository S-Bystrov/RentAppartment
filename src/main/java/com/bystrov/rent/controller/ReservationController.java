package com.bystrov.rent.controller;

import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReservationService;
import com.bystrov.rent.validator.ReservationDateValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationDateValidator validator;

    public ReservationController(ReservationService reservationService,
                                 ReservationDateValidator validator) {
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
