package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;
/*
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }*/

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String newRegistration(@Valid UserDTO userDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        model.addAttribute("userDTO", userDTO);
        if(bindingResult.hasErrors()){
            Map<String, String> collect = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(collect);
            return "/registration";
        } else {
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                model.addAttribute("errorPassword", "Passwords don't match");
                return "registration";
            }
            if (userService.findByUsername(userDTO.getUsername()) == null) {
                userService.saveUser(userDTO);
            } else {
                model.addAttribute("errorUsername", "A user with this username already exists");
                return "registration";
            }
            return "redirect:/";
        }
    }

    @GetMapping("/profile/{idUser}")
    public String getUserInfoPage(@PathVariable("idUser") long idUser, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getName().equals("anonymousUser")){
            String username = authentication.getName();
            UserDTO user = userService.findByUsername(username);
            Long id = user.getId();
            boolean checkUser = false;
            if(id.equals(idUser)){
                checkUser = true;
            }
            model.addAttribute("checkUser", checkUser);
        }
        UserDTO userDTO = userService.findById(idUser);
        model.addAttribute("userDTO", userDTO);

        return "user_info";
    }

    @GetMapping("/profile/{idUser}/update")
    public String getUpdateInfoPage(@PathVariable("idUser") long idUser, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("idUser", idUser);
        return "update_info";
    }

    @PostMapping("/profile/{idUser}/update")
    public String updateInfo(@PathVariable("idUser") long idUser,
                             @RequestParam("avatar") MultipartFile file,
                             UserDTO userDTO) throws IOException {
        String avatarName = ControllerUtils.saveFile(file, uploadPath);
        if(avatarName !=null){
            userDTO.setAvatarName(avatarName);
        }
        userService.update(userDTO, idUser);
        return "redirect:/profile/" + idUser;
    }
}
