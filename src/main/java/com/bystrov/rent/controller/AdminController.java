package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.CountryService;
import com.bystrov.rent.service.ReviewService;
import com.bystrov.rent.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final AdvertisementService advertisementService;
    private final CountryService countryService;

    public AdminController(UserService userService,
                           ReviewService reviewService,
                           AdvertisementService advertisementService,
                           CountryService countryService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.advertisementService = advertisementService;
        this.countryService = countryService;
    }

    @GetMapping("/user-list")
    public String getUsersPage(Model model){
        model.addAttribute("userList", userService.getAll());
        return "user_list";
    }

    @GetMapping("/country-list")
    public String getCountriesPage(Model model){
        addModel(model);
        return "countries_list";
    }

    @PostMapping("/country-list/edit")
    public String editCountry(CountryDTO country,
                              Model model){
        countryService.update(country);
        addModel(model);
        return "countries_list";
    }

    @GetMapping("/country-list/{idCountry}/remove")
    public String removeCountry(@PathVariable Long idCountry,
                                Model model) {
        countryService.deleteById(idCountry);
        addModel(model);
        return "countries_list";
    }

    @PostMapping("/country-list/create-new")
    public String createCountry(CountryDTO countryDTO,
                                Model model){
        countryService.save(countryDTO);
        addModel(model);
        return "countries_list";
    }


    private void addModel(Model model){
        CountryDTO countryDTO = new CountryDTO();
        model.addAttribute("countryDTO", countryDTO);
        model.addAttribute("countryList", countryService.getAll());
    }
}


