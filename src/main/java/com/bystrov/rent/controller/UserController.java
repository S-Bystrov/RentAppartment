package com.bystrov.rent.controller;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.UserService;
import com.bystrov.rent.validator.UserUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class UserController {

    @Autowired
    private UserUpdateValidator updateValidator;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/profile/{idUser}")
    public String getUserInfoPage(@PathVariable("idUser") Long idUser, Model model) {
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

    @GetMapping("/profile/update")
    public String getUpdateInfoPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "update_info";
    }

    @PostMapping("/profile/update")
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
}
