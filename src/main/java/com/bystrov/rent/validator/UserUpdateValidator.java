package com.bystrov.rent.validator;

import com.bystrov.rent.DTO.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserUpdateValidator implements Validator {

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
    }
}
