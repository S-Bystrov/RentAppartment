package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

/*    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }*/

    @GetMapping("/login")
    public String getLoginPage() {
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
                                  Model model) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            model.addAttribute("errorPassword", "Passwords don't match");
            return "registration";
        }
        if (userService.findByUsername(userDTO.getUsername()) == null) {
            userService.saveUser(userDTO);
        } else {
            model.addAttribute("usernameError", "A user with this username already exists");
            return "registration";
        }
       return "advertisement";
    }

    @GetMapping("/user-info")
    public String getUserInfoPage(@AuthenticationPrincipal User authenticalUser, Model model) {
        Long id = authenticalUser.getId();
        UserDTO userDTO = userService.findById(id);
        model.addAttribute(userDTO);
        return "user_info";
    }

    @GetMapping("update-info")
    public String getUpdateInfoPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "update_info";
    }

    @PostMapping("update-info")
    public String updateInfo(@AuthenticationPrincipal User authenticalUser,
                             UserDTO userDTO, Model model){
        String username = authenticalUser.getUsername();
        userService.update(userDTO, username);
        return "redirect:/user-info";
    }

}
