package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationValidator registrationValidator;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error",  required = false) String error,
                               Model model){
        if(error != null){
            model.addAttribute("errorMessage", "error.login");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String newRegistration(UserDTO userDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        registrationValidator.validate(userDTO, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("userDTO", userDTO);
            return "registration";
        }
        userService.saveUser(userDTO);
        model.addAttribute("message");
        return "redirect:/";
    }
}
