package com.bystrov.rent.controller;

import com.bystrov.rent.domain.User;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String newRegistration(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "confirmPassword") String confirmPassword,
                                  Model model) {
        User user = new User();
        user.setLogin(login);
        if (userService.findByLogin(login) != null) {
            if (password.equals(confirmPassword)) {
                user.setPassword(password);
                user.setEmail(email);
                userService.saveUser(user);
            } else {
                model.addAttribute("errorConfirmPassword", "Passwords don't match");
            }
        } else {
            model.addAttribute("errorLogin", "A user with this username already exists");
        }
        return "/advertisement";
    }

    @GetMapping("/user-info")
    public String getUserInfoPage() {
        return "userInfo";
    }
}
