package com.bystrov.rent.controller;

import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReservationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/advertisement/{idAdvertisement}/reservation")
    public String getReservationPage(@PathVariable Long idAdvertisement,
                                     @AuthenticationPrincipal User authenticalUser){
        reservationService.saveReservation(idAdvertisement, authenticalUser);
        return "redirect:/";
    }

    @GetMapping("/profile/reservation")
    public String getUserReservationPage(Model model,
                                         @AuthenticationPrincipal User authenticalUser){
        model.addAttribute("reservationList", reservationService.getAllByUserId(authenticalUser.getId()));
        return "user_reservation";
    }
}
