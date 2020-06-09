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
public class UserUpdateValidator implements Validator {

    private final UserService userService;

    public UserUpdateValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        if(!StringUtils.isBlank(user.getName())){
            if(!user.getName().matches("[a-zA-Zа-яА-ЯёЁ]{2,15}")){
                errors.rejectValue("name", "error.name");
            }
        }
        if(!StringUtils.isBlank(user.getSurname())){
            if(!user.getSurname().matches("[a-zA-Zа-яА-ЯёЁ]{1,15}")){
                errors.rejectValue("surname", "error.surname");
            }
        }
        if(!StringUtils.isBlank(user.getCard())){
            if(!user.getCard().matches("[0-9]{16}")){
                errors.rejectValue("card", "error.card");
            }
        }
        if(!StringUtils.isBlank(user.getPaymentAccount())){
            if(!user.getPaymentAccount().matches("[0-9]{20}")){
                errors.rejectValue("paymentAccount", "error.paymentAccount");
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
    }

    private boolean checkEmail(String email){
        Pattern emailPattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }
}
