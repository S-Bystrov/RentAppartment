package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.ReservationValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvertisementInfoController {

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final ReservationValidator reservationValidator;

    public AdvertisementInfoController(AdvertisementService advertisementService,
                                   UserService userService,
                                   ReservationValidator reservationValidator){
        this.advertisementService = advertisementService;
        this.userService = userService;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping("/advertisement/{idAdvertisement}")
    public String getAdvertisementInfoPage(@PathVariable Long idAdvertisement,
                                           @AuthenticationPrincipal User authenticalUser,
                                           Model model){
        model.addAttribute("reservationDTO", new ReservationDTO());
        model.addAttribute("checkDate", false);
        model.addAttribute("checkCard", userService.checkCard(authenticalUser));
        model.addAttribute("checkUser", advertisementService.checkUser(idAdvertisement, authenticalUser));
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
        return "advertisement_info";
    }

    @GetMapping("check-availability")
    public String checkAvailability(@RequestParam Long idAdvertisement,
                                    ReservationDTO reservationDTO,
                                    @AuthenticationPrincipal User authenticalUser,
                                    BindingResult bindingResult,
                                    Model model){
        reservationValidator.validate(reservationDTO, bindingResult);
        model.addAttribute("checkCard", userService.checkCard(authenticalUser));
        model.addAttribute("checkUser", advertisementService.checkUser(idAdvertisement, authenticalUser));
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
        if(bindingResult.hasErrors()){
            model.addAttribute("reservationDTO", reservationDTO);
            return "advertisement_info";
        }
        boolean checkDate = advertisementService.checkBydate(reservationDTO);
        model.addAttribute(checkDate);
        return "advertisement_info";
    }
}
