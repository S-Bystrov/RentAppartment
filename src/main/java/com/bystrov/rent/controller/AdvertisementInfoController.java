package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.DTO.ReservationDateDTO;
import com.bystrov.rent.DTO.ReviewDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.ReservationService;
import com.bystrov.rent.service.ReviewService;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.ReservationDateValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/advertisement/{idAdvertisement}")
public class AdvertisementInfoController {

    private static final int PAGE = 1;
    private static final int SIZE = 10;

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final ReservationService reservationService;
    private final ReservationDateValidator reservationDateValidator;
    private final ReviewService reviewService;

    public AdvertisementInfoController(AdvertisementService advertisementService,
                                       UserService userService,
                                       ReservationService reservationService,
                                       ReservationDateValidator reservationDateValidator,
                                       ReviewService reviewService){
        this.advertisementService = advertisementService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationDateValidator = reservationDateValidator;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getAdvertisementInfoPage(@PathVariable Long idAdvertisement,
                                           @AuthenticationPrincipal User authenticalUser,
                                           Model model){
        model.addAttribute("checkDate", null);
        model.addAttribute("reservationDate", new ReservationDateDTO());
        modelAddAtribute(idAdvertisement, authenticalUser, model);
        return "advertisement_info";
    }


    @PostMapping("/check-availability")
    @PreAuthorize("hasAuthority('USER')")
    public String checkAvailability(@PathVariable Long idAdvertisement,
                                    @AuthenticationPrincipal User authenticalUser,
                                    @ModelAttribute("reservationDate") ReservationDateDTO reservationDate,
                                    BindingResult bindingResult,
                                    Model model) {
        reservationDateValidator.validate(reservationDate, bindingResult);
        modelAddAtribute(idAdvertisement, authenticalUser, model);
        if(bindingResult.hasErrors()){
            model.addAttribute("checkDate", null);
            model.addAttribute("reservationDate", reservationDate);
            return "advertisement_info";
        }
        boolean checkDate = advertisementService.checkByDate(idAdvertisement, reservationDate);
        if(checkDate){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            model.addAttribute("arrivalDate", reservationDate.getArrivalDate().format(format));
            model.addAttribute("departureDate", reservationDate.getDepartureDate().format(format));
        }
        model.addAttribute("checkDate",  checkDate);
        return "advertisement_info";
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasAuthority('USER')")
    public String addReservation(@PathVariable Long idAdvertisement,
                                 @AuthenticationPrincipal User authenticalUser,
                                 ReservationDTO reservationDTO,
                                 @RequestParam("checkArrivalDate") String checkArrivalDate,
                                 @RequestParam("checkDepartureDate") String checkDepartureDate,
                                 Model model) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate arrivalDate = StringUtils.isNotBlank(checkArrivalDate) ? LocalDate.parse(checkArrivalDate, format) : null;
        LocalDate departureDate = StringUtils.isNotBlank(checkDepartureDate) ? LocalDate.parse(checkDepartureDate, format) : null;
        if(reservationDTO.getArrivalDate().isEqual(arrivalDate) && reservationDTO.getDepartureDate().isEqual(departureDate)){
            reservationService.saveReservation(idAdvertisement, authenticalUser, reservationDTO);
            return "redirect:/profile/reservation";
        } else {
            model.addAttribute("checkDate", null);
            modelAddAtribute(idAdvertisement, authenticalUser, model);
            model.addAttribute("reservationDate", reservationDTO);
            return "advertisement_info";
        }
    }

    @PostMapping("/add-review")
    public String addNewReview(@PathVariable Long idAdvertisement,
                               @AuthenticationPrincipal User authenticalUser,
                               ReviewDTO reviewDTO){
        reviewDTO.setUser(authenticalUser);
        reviewService.saveReview(reviewDTO, idAdvertisement, authenticalUser.getUsername());
        return "redirect:/advertisement/" + idAdvertisement;
    }

    @GetMapping("/all-review")
    public String getAllReviewPage(@PathVariable Long idAdvertisement,
                                   @RequestParam Optional<Integer> page,
                                   @RequestParam Optional<Integer> size,
                                   Model model){
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<ReviewDTO> reviewDTOPage = reviewService.findPagination(PageRequest.of(currentPage-1, pageSize), idAdvertisement);
        ControllerUtils.getPaginationPage(model, reviewDTOPage);
        List<ReviewDTO> reviewDTOList = reviewService.findAllByIdAdvertisement(idAdvertisement);
        model.addAttribute("reviewDTOList", reviewDTOList);
        return "all_review";
    }

    private void modelAddAtribute(Long idAdvertisement, User user, Model model) {
        model.addAttribute("reviewDTO", new ReviewDTO());
        model.addAttribute("reservationDTO", new ReservationDTO());
        model.addAttribute("checkCard", userService.checkCard(user));
        model.addAttribute("checkUser", advertisementService.checkUser(idAdvertisement, user));
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
    }
}
