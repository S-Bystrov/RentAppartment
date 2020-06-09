package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.UserUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserUpdateValidator updateValidator;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/{idUser}")
    public String getUserInfoPage(@PathVariable("idUser") Long idUser,
                                  @AuthenticationPrincipal User authenticalUser,
                                  Model model) {
        boolean checkUser = false;
        if(authenticalUser != null){
            if(authenticalUser.getId() == idUser){
                checkUser = true;
            }
        }
        UserDTO userDTO = userService.findById(idUser);
        model.addAttribute("isSendCode", false);
        model.addAttribute("checkUser", checkUser);
        model.addAttribute("userDTO", userDTO);

        return "user_info";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public String getUpdateInfoPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "update_info";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public String updateInfo(@AuthenticationPrincipal User authenticalUser,
                             @RequestParam("avatar") MultipartFile file,
                             @Valid UserDTO userDTO,
                             BindingResult bindingResult,
                             Model model) throws IOException {
        updateValidator.validate(userDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "update_info";
        }
        String avatarName = ControllerUtils.saveFile(file, uploadPath);
        Long idUser = authenticalUser.getId();
        if(avatarName !=null){
            userDTO.setAvatarName(avatarName);
        }
        userService.update(userDTO, idUser);
        return "redirect:/profile/" + idUser;
    }

    @GetMapping("/send-code")
    @PreAuthorize("hasAuthority('USER')")
    public String sendCode(@AuthenticationPrincipal User authenticalUser,
                           Model model) {
        boolean checkSendCode = userService.sendCode(authenticalUser);
        model.addAttribute("checkUser", true);
        model.addAttribute("isSendCode", checkSendCode);
        model.addAttribute("userDTO", authenticalUser);
        return "user_info";
    }


    @GetMapping("/activate/{code}")
    public String activation(@AuthenticationPrincipal User authenticalUser,
                             @PathVariable String code,
                             Model model){
        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("messageActivated", "user.activatedSuccess");
        } else {
            model.addAttribute("messageCodeNotFound", "user.codeNotFound");
        }
        model.addAttribute("checkUser", true);
        model.addAttribute("userDTO", userService.findById(authenticalUser.getId()));
        return "user_info";
    }
}
