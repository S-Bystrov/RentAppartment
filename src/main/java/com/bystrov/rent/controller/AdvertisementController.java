package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import com.bystrov.rent.service.CountryService;
import com.bystrov.rent.service.ImageService;
import com.bystrov.rent.validator.AdvertisementValidator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AdvertisementController {

    @Value("${upload.path}")
    private String uploadPath;

    private static final int PAGE = 1;
    private static final int SIZE = 6;


    private final AdvertisementService advertisementService;
    private final ImageService imageService;
    private final CountryService countryService;
    private final AdvertisementValidator advertisementValidator;

    public AdvertisementController(AdvertisementService advertisementService,
                                   ImageService imageService,
                                   CountryService countryService,
                                   AdvertisementValidator advertisementValidator){
        this.advertisementService = advertisementService;
        this.imageService = imageService;
        this.countryService = countryService;
        this.advertisementValidator = advertisementValidator;
    }

    @GetMapping("/")
    public String advertisementGetPage(@RequestParam Optional<Integer> page,
                                       @RequestParam Optional<Integer> size,
                                       Model model) {
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<AdvertisementDTO> advertisementDTOPage =
                advertisementService.findPaginated(PageRequest.of(currentPage-1, pageSize));
        ControllerUtils.getPaginationPage(model, advertisementDTOPage);
        model.addAttribute("countryDTOList", countryService.getAll());
        return "main";
    }

    @GetMapping("filter")
    public String filter(@RequestParam(required = false) Long filterCountry,
                         @RequestParam(required = false) String filterCity,
                         @RequestParam(required = false) String filterArrivalDate,
                         @RequestParam(required = false) String filterDepartureDate,
                         @RequestParam Optional<Integer> page,
                         @RequestParam Optional<Integer> size,
                         Model model) throws ParseException {
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<AdvertisementDTO> advertisementDTOPage =
                advertisementService.findPaginatedByFilter(PageRequest.of(currentPage-1, pageSize),
                        filterCountry, filterCity, filterArrivalDate, filterDepartureDate);
        ControllerUtils.getPaginationPage(model, advertisementDTOPage);
        model.addAttribute("countryDTOList", countryService.getAll());
        return "main";
    }


    @GetMapping("/new-advertisement")
    @PreAuthorize("hasAuthority('USER')")
    public String getNewAdvertisementPage(Model model) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        List<CountryDTO> countryDTOList = countryService.getAll();
        model.addAttribute("advertisementDTO", advertisementDTO);
        model.addAttribute("countryDTOList", countryDTOList);
        return "new_advertisement";
    }

    @PostMapping("/new-advertisement")
    @PreAuthorize("hasAuthority('USER')")
    public String addNewAdvertisement(@RequestParam("image") MultipartFile[] files,
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
        List<String> imageList = new ArrayList<>();
        for (MultipartFile image : files) {
            String nameImage = ControllerUtils.saveFile(image, uploadPath);
            imageList.add(nameImage);
        }
        AdvertisementDTO newAdvertisement = advertisementService.saveAdvertisement(advertisementDTO);
        imageService.saveImage(imageList, newAdvertisement.getIdAdvertisement());
        return "redirect:/";
    }

    @GetMapping("/profile/advertisement")
    public String getUserAdvertisementPage(@AuthenticationPrincipal User authenticalUser,
                                           @RequestParam Optional<Integer> page,
                                           @RequestParam Optional<Integer> size,
                                           Model model) {
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<AdvertisementDTO> advertisementDTOPage =
                advertisementService.findPaginatedByUserId(PageRequest.of(currentPage-1, pageSize),
                        authenticalUser.getId());
       ControllerUtils.getPaginationPage(model, advertisementDTOPage);
        return "user_advertisement";
    }

    @GetMapping("/profile/advertisement/{idAdvertisement}/remove")
    @PreAuthorize("hasAuthority('USER')")
    public String removeAdvertisement(@PathVariable Long idAdvertisement,
                                      @AuthenticationPrincipal User authenticalUser,
                                      @RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> size,
                                      Model model){
        advertisementService.deleteById(idAdvertisement);
        int currentPage = page.orElse(PAGE);
        int pageSize = size.orElse(SIZE);
        Page<AdvertisementDTO> advertisementDTOPage =
                advertisementService.findPaginatedByUserId(PageRequest.of(currentPage-1, pageSize),
                        authenticalUser.getId());
        ControllerUtils.getPaginationPage(model, advertisementDTOPage);
        return "redirect:/profile/advertisement";
    }
}
