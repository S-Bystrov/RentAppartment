package com.bystrov.rent.validator;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserRegistrationValidator implements Validator {

    private final UserService userService;

    public UserRegistrationValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;
        if(StringUtils.isBlank(user.getUsername())){
            errors.rejectValue("username", "error.username.empty");
        } else {
            if(user.getUsername().length() < 4 || user.getUsername().length() > 10){
                errors.rejectValue("username", "error.username.size");
            }
            if(userService.findByUsername(user.getUsername()) != null){
                errors.rejectValue("username", "error.username.exist");
            }
            if(!latinLettersOnly(user.getUsername())){
                errors.rejectValue("username", "error.username.symbol");
            }
        }


        if(StringUtils.isBlank(user.getEmail())){
            errors.rejectValue("email", "error.email.empty");
        } else {
            if(!checkEmail(user.getEmail())){
                errors.rejectValue("email", "error.email.valid");
            }
            if(userService.findByEmail(user.getEmail()) != null){
                errors.rejectValue("email", "error.email.exist");
            }
        }


        if(StringUtils.isBlank(user.getPassword())) {
            errors.rejectValue("password", "error.password.empty");
        } else {
            if(user.getPassword().length() < 6 || user.getPassword().length() > 30){
                errors.rejectValue("password", "error.password.size");
            }
            if(!user.getPassword().equals(user.getConfirmPassword())){
                errors.rejectValue("confirmPassword", "error.password.confirm");
            }
        }
    }


    private boolean checkEmail(String email){
        Pattern emailPattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    private boolean latinLettersOnly(String string){
        Pattern latinPattern = Pattern.compile("[a-zA-Z]");
        Matcher latinMatcher = latinPattern.matcher(string);
        return latinMatcher.find();
    }

}
