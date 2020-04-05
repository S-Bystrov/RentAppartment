package com.bystrov.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdvertisementController {
    @GetMapping("/advertisement")
    public String advertisementGetPage(){
        return "advertisement";
    }
}
