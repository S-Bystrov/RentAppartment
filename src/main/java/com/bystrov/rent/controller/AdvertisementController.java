package com.bystrov.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdvertisementController {
    @GetMapping("/advertisement")
    public String advertisementGetPage(){
        return "advertisement";
    }
}
