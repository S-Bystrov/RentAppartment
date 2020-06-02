package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.ReservationService;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.ReservationValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/advertisement/{idAdvertisement}")
public class AdvertisementInfoController {

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public AdvertisementInfoController(AdvertisementService advertisementService,
                                   UserService userService,
                                   ReservationService reservationService,
                                   ReservationValidator reservationValidator){
        this.advertisementService = advertisementService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping
    public String getAdvertisementInfoPage(@PathVariable Long idAdvertisement,
                                           @AuthenticationPrincipal User authenticalUser,
                                           Model model){
        model.addAttribute("checkDate", null);
        modelAddAtribute(idAdvertisement, authenticalUser, model);
        return "advertisement_info";
    }


    @GetMapping("/check-availability")
    public String checkAvailability(@PathVariable Long idAdvertisement,
                                    @RequestParam String arrivalDate,
                                    @RequestParam String departureDate,
                                    @AuthenticationPrincipal User authenticalUser,
                                    Model model){
        boolean checkDate = advertisementService.checkByDate(idAdvertisement, arrivalDate, departureDate);
        if(checkDate){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            model.addAttribute("arrivalDateDefault", LocalDate.parse(arrivalDate, format));
            model.addAttribute("departureDate", departureDate);
        }
        model.addAttribute("checkDate",  checkDate);
        modelAddAtribute(idAdvertisement, authenticalUser, model);
        return "advertisement_info";
    }

    @PostMapping("/reservation")
    public String addReservation(@PathVariable Long idAdvertisement,
                                 @AuthenticationPrincipal User authenticalUser,
                                 ReservationDTO reservationDTO,
                                 @RequestParam("checkArrivalDate") String arrivalDate,
                                 @RequestParam("checkDepartureDAte") String departureDate,
                                 BindingResult result,
                                 Model model){
        reservationValidator.validate(reservationDTO, result);
        if(result.hasErrors()){
            model.addAttribute("reservationDTO", reservationDTO);
            return "redirect:/advertisement/" + idAdvertisement;
        }
        reservationService.saveReservation(idAdvertisement, authenticalUser, reservationDTO);
        return "user_reservation";
    }

    private void modelAddAtribute(Long idAdvertisement, User user, Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        model.addAttribute("checkCard", userService.checkCard(user));
        model.addAttribute("checkUser", advertisementService.checkUser(idAdvertisement, user));
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
    }
}
