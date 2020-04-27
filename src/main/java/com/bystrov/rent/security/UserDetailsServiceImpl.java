package com.bystrov.rent.security;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserService userService;

 /*   @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByLogin(login);
        if(userDTO == null){
            throw new UsernameNotFoundException(login);
        }
        UserDetails userDetails = userDTO;
        return userDetails;
    }*/
}
