package com.bystrov.rent.service;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);
    List<UserDTO> getAll();
    UserDTO saveUser(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(User user);
    void deleteById(Long id);
    UserDTO findByLogin (String login);
}
