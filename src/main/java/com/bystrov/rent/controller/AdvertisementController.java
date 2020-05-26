package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.CountryService;
import com.bystrov.rent.service.ImageService;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.AdvertisementValidator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class AdvertisementController {

    @Value("upload.path")
    private String uploadPath;

    private final UserService userService;
    private final AdvertisementService advertisementService;
    private final ImageService imageService;
    private final CountryService countryService;
    private final AdvertisementValidator advertisementValidator;

    public AdvertisementController(AdvertisementService advertisementService,
                                   ImageService imageService,
                                   CountryService countryService,
                                   AdvertisementValidator advertisementValidator,
                                   UserService userService){
        this.advertisementService = advertisementService;
        this.imageService = imageService;
        this.countryService = countryService;
        this.advertisementValidator = advertisementValidator;
        this.userService = userService;
    }

    @GetMapping("/")
    public String advertisementGetPage(Model model) {
        model.addAttribute("countryDTOList", countryService.getAll());
        model.addAttribute("advertisementList", advertisementService.getAllFree());
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam Long filterCountry,
                         @RequestParam String filterCity,
                         Model model) {
        List<AdvertisementDTO> advertisementList;
        if ((filterCity == null || filterCity.isEmpty()) && filterCountry == null) {
            advertisementList = advertisementService.getAll();
        } else {
            advertisementList = advertisementService.findByFilter(filterCountry, filterCity);
        }
        model.addAttribute("advertisementList", advertisementList);
        model.addAttribute("countryDTOList", countryService.getAll());
        return "main";
    }

    @GetMapping("/new-advertisement")
    public String getNewAdvertisementPage(Model model) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        List<CountryDTO> countryDTOList = countryService.getAll();
        model.addAttribute("advertisementDTO", advertisementDTO);
        model.addAttribute("countryDTOList", countryDTOList);
        return "new_advertisement";
    }

    @PostMapping("/new-advertisement")
    public String addNewAdvertisement(@RequestParam("image") MultipartFile file,
                                      @AuthenticationPrincipal User authenticalUser,
                                      AdvertisementDTO advertisementDTO,
                                      BindingResult bindingResult,
                                      Model model) throws IOException {
        advertisementValidator.validate(advertisementDTO, bindingResult);
        if(bindingResult.hasErrors()){
            List<CountryDTO> countryDTOList = countryService.getAll();
            model.addAttribute("countryDTOList", countryDTOList);
            model.addAttribute("advertisementDTO", advertisementDTO);
            return "new_advertisement";
        }
        advertisementDTO.setUser(authenticalUser);
        String nameImage = ControllerUtils.saveFile(file, uploadPath);
        AdvertisementDTO newAdvertisement = advertisementService.saveAdvertisement(advertisementDTO);
        imageService.saveImage(nameImage, newAdvertisement.getIdAdvertisement());
        return "redirect:/";
    }

    @GetMapping("/profile/advertisement")
    public String getUserAdvertisementPage(@AuthenticationPrincipal User authenticalUser,
                                           Model model) {
        model.addAttribute("advertisementList", advertisementService.getAllByUserId(authenticalUser.getId()));
        return "user_advertisement";
    }

    @GetMapping("/profile/advertisement/{idAdvertisement}/remove")
    public String removeAdvertisement(@PathVariable("idAdvertisement") Long idAdvertisement,
                                      @AuthenticationPrincipal User authenticalUser,
                                      Model model){
        advertisementService.deleteById(idAdvertisement);
        model.addAttribute("advertisementList", advertisementService.getAllByUserId(authenticalUser.getId()));
        return "redirect:/profile/advertisement";
    }

    @GetMapping("/advertisement/{idAdvertisement}")
    public String getAdvertisementInfoPage(@PathVariable("idAdvertisement") Long idAdvertisement,
                                           @AuthenticationPrincipal User authenticalUser,
                                           Model model) {
        model.addAttribute("checkCard", userService.checkCard(authenticalUser));
        model.addAttribute("checkUser", advertisementService.checkUser(idAdvertisement, authenticalUser));
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
        return "advertisement_info";
    }

    @GetMapping("/profile/advertisement/{idAdvertisement}/change-status")
    public String changeStatusAdvertisement(@PathVariable Long idAdvertisement,
                                            @RequestParam("status") String status,
                                            @AuthenticationPrincipal User authenticalUser,
                                            Model model){
        advertisementService.update(idAdvertisement, status);
        model.addAttribute("advertisementList", advertisementService.getAllByUserId(authenticalUser.getId()));
        return "redirect:/profile/advertisement";
    }
}
