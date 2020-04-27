package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserDTOParser {

    public UserDTO createDTOFromDomain (User user){
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .age(user.getAge())
                .name(user.getName())
                .surname(user.getSurname())
                .card(user.getCard())
                .password(user.getPassword())
                .paymentAccount(user.getPaymentAccount())
                .userType(user.getUserType())
                .build();
        return userDTO;
    }

    public User createDomainFromDTO (UserDTO userDTO){
        User user = User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .card(userDTO.getCard())
                .password(userDTO.getPassword())
                .paymentAccount(userDTO.getPaymentAccount())
                .userType(userDTO.getUserType())
                .build();
        return user;
    }
}
