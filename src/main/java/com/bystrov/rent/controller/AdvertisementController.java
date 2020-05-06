package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/")
    public String advertisementGetPage(Model model){
        model.addAttribute("advertisementList", advertisementService.getAll());
        return "advertisement";
    }


    @GetMapping("/new-advertisement")
    public String getNewAdvertisementPage(Model model) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        model.addAttribute("advertisementDTO", advertisementDTO);
        return "new_advertisement";
    }

    @PostMapping("/new-advertisement")
    public String addNewAdvertisement(@AuthenticationPrincipal User authenticalUser,
                                      AdvertisementDTO advertisementDTO) {
        advertisementDTO.setUser(authenticalUser);
        advertisementService.saveAdvertisement(advertisementDTO);
        return "redirect:/";
    }
}
