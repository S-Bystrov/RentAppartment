package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.AddressDTO;
import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
/*import com.bystrov.rent.service.CountryService;*/
import com.bystrov.rent.service.CountryService;
import com.bystrov.rent.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
public class AdvertisementController {

    @Value("upload.path")
    private String uploadPath;

    private final AdvertisementService advertisementService;
    private final ImageService imageService;
    private final CountryService countryService;

    public AdvertisementController(AdvertisementService advertisementService,
                                   ImageService imageService,
                                   CountryService countryService)                                    {
        this.advertisementService = advertisementService;
        this.imageService = imageService;
        this.countryService = countryService;
    }

    @GetMapping("/")
    public String advertisementGetPage(Model model) {
        model.addAttribute("advertisementList", advertisementService.getAll());
        return "main";
    }

/*    @PostMapping("filter")
    public String filter(@RequestParam String filterCountry,
                         @RequestParam String filterCity,
                         Model model){
        List<AdvertisementDTO> advertisementList = advertisementService.findByFilter(filterCountry, filterCity);
        model.addAttribute("advertisementList", advertisementList);
        return "/";
    }*/


    @GetMapping("/profile/{idUser}/new-advertisement")
    public String getNewAdvertisementPage(@PathVariable("idUser") Long idUser,
                                          Model model) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        //AddressDTO addressDTO = new AddressDTO();
        List<CountryDTO> countryDTOList = countryService.getAll();
        model.addAttribute("advertisementDTO", advertisementDTO);
        model.addAttribute("countryDTOList", countryDTOList);
        //model.addAttribute("addressDTO", addressDTO);
        model.addAttribute("idUser", idUser);
        return "new_advertisement";
    }

    @PostMapping("/profile/{idUser}/new-advertisement")
    public String addNewAdvertisement(@PathVariable("idUser") Long idUser,
                                      @RequestParam("image") MultipartFile file,
                                      @AuthenticationPrincipal User authenticalUser,
                                      @Valid AdvertisementDTO advertisementDTO,
                                      BindingResult bindingResult,
                                      Model model) throws IOException {
        model.addAttribute("advertisementDTO", advertisementDTO);
        if(bindingResult.hasErrors()){
            Map<String, String> collect = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(collect);
            return "new_advertisement";
        } else {
            advertisementDTO.setUser(authenticalUser);
            String nameImage = ControllerUtils.saveFile(file, uploadPath);
            AdvertisementDTO newAdvertisement = advertisementService.saveAdvertisement(advertisementDTO);
            imageService.saveImage(nameImage, newAdvertisement.getIdAdvertisement());
        }
        return "redirect:/";
    }

    @GetMapping("/profile/{idUser}/advertisement")
    public String getUserAdvertisementPage(@PathVariable("idUser") long id,
                                           Model model) {
        model.addAttribute("advertisementList", advertisementService.getAllByUserId(id));
        return "user_advertisement";
    }



    @GetMapping("/advertisement/{idAdvertisement}")
    public String getAdvertisementInfoPage(@PathVariable("idAdvertisement") long idAdvertisement,
                                           Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkUser = true;
        if (!authentication.getName().equals("anonymousUser")) {
            String username = authentication.getName();
            String usernameByIdAdvertisement = advertisementService.findUsernameByIdAdvertisement(idAdvertisement);
            if (username.equals(usernameByIdAdvertisement)) {
                checkUser = false;
            }
        }
        model.addAttribute("checkUser", checkUser);
        model.addAttribute("advertisementDTO", advertisementService.findById(idAdvertisement));
        return "advertisement_info";
    }
}
