package com.bystrov.rent.controller;

import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/profile/reservation")
    public String getUserReservationPage(Model model,
                                         @AuthenticationPrincipal User authenticalUser){
        model.addAttribute("reservationList", reservationService.getAllByUserId(authenticalUser.getId()));
        return "user_reservation";
    }
}
