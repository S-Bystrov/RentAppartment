package com.bystrov.rent.validator;

import com.bystrov.rent.DTO.AddressDTO;
import com.bystrov.rent.DTO.AdvertisementDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AdvertisementValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AdvertisementDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AdvertisementDTO advertisement = (AdvertisementDTO) o;

        if(StringUtils.isBlank(advertisement.getDescription())) {
            errors.rejectValue("description", "error.description.empty");
        } else {
            if(advertisement.getDescription().length() > 700){
                errors.rejectValue("description", "error.description.size");
            }
        }
        if(StringUtils.isBlank(advertisement.getPrice())) {
            errors.rejectValue("price", "error.price.empty");
        } else {
            if(!advertisement.getPrice().matches("[0-9]+[.,]?[0-9]{0,2}")){
                errors.rejectValue("price", "error.price");
            }
        }


        AddressDTO address = advertisement.getAddress();
        if(address.getCountry().getIdCountry() == null){
            errors.rejectValue("address.country.idCountry", "error.country");
        }
        if(StringUtils.isBlank(address.getCity())) {
            errors.rejectValue("address.city", "error.city.empty");
        } else {
            if(!address.getCity().matches("[a-zA-Zа-яА-ЯёЁ\\-]{3,20}")) {
                errors.rejectValue("address.city", "error.city");
            }
        }


        if(StringUtils.isBlank(address.getHouse())) {
            errors.rejectValue("address.house", "error.house.empty");
        } else {
            if(!address.getHouse().matches("[0-9]{1,3}")){
                errors.rejectValue("address.house", "error.house");
            }
        }

        if(StringUtils.isBlank(address.getStreet())) {
            errors.rejectValue("address.street", "error.street.empty");
        } else {
            if(!address.getStreet().matches("[a-zA-Zа-яА-ЯёЁ0-9\\-]{3,20}")) {
                errors.rejectValue("address.street", "error.street");
            }
        }

        if(!address.getBuilding().matches("[a-zA-Zа-яА-ЯёЁ0-9]{0,2}")){
            errors.rejectValue("address.building", "error.building");
        }

        if(!address.getFlat().matches("[0-9]{0,4}")){
            errors.rejectValue("address.flat", "error.flat");
        }
    }

}
