package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String getRegistrationPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String newRegistration(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  Model model) {
        if (!userForm.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordError", "Passwords don't match");
            return "registration";
        }
        if (userService.findByLogin(userForm.getLogin()) != null) {
            userService.saveUser(userForm);
        } else {
            model.addAttribute("usernameError", "A user with this username already exists");
            return "registration";
        }
        /*user user = new user();
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
        }*/
      /*  return "/redirect:";
    }*/

    @GetMapping("/user-info")
    public String getUserInfoPage() {
        return "userInfo";
    }
}
