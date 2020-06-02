package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.UserRole;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.CountryService;
import com.bystrov.rent.service.ReviewService;
import com.bystrov.rent.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private static final int PAGE = 1;
    private static final int SIZE = 20;

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
    public String getUsersPage(@RequestParam Optional<Integer> page,
                               @RequestParam Optional<Integer> size,
                               Model model){
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<UserDTO> userDTOPage =
                userService.findPaginated(PageRequest.of(currentPage-1, pageSize));
        ControllerUtils.getPaginationPage(model, userDTOPage);
        model.addAttribute("roles", UserRole.values());
        return "user_list";
    }

    @PostMapping("/user-list/{idUser}/edit")
    public String editUser(@PathVariable Long idUser,
                           @RequestParam Map<String, String> form,
                           Model model){
        UserDTO user = userService.findById(idUser);
        user.getRoles().clear();
        Set<String> roles = Arrays.stream(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toSet());
        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(UserRole.valueOf(key));
            }
        }
        userService.update(user, idUser);
        return "redirect:/user-list";
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


