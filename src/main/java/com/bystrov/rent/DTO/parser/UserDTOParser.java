package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserDTOParser {

    public UserDTO createUserDTOFromDomain(User user){
        if (user == null){
            return null;
        }
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .name(user.getName())
                .surname(user.getSurname())
                .card(user.getCard())
                .password(user.getPassword())
                .paymentAccount(user.getPaymentAccount())
                .roles(user.getRoles())
                .build();
        return userDTO;
    }

    public User createUserDomainFromDTO(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .card(userDTO.getCard())
                .password(userDTO.getPassword())
                .paymentAccount(userDTO.getPaymentAccount())
                .roles(userDTO.getRoles())
                .build();
        return user;
    }
}
