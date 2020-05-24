package com.bystrov.rent.controller;

import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.ReviewService;
import com.bystrov.rent.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    private final ReviewService reviewService;

    private final AdvertisementService advertisementService;

    public AdminController(UserService userService, ReviewService reviewService, AdvertisementService advertisementService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.advertisementService = advertisementService;
    }

    @GetMapping("/admin-menu")
    public String getAdminPage(){
        return "admin_page";
    }

    @GetMapping("/user-list")
    public String getUsersPage(Model model){
        model.addAttribute("userList", userService.getAll());
        return "user_list";
    }

}