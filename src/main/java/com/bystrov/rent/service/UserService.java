package com.bystrov.rent.service;

import com.bystrov.rent.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);
    List<UserDTO> getAll();
    UserDTO saveUser(UserDTO userDTO);
    void update(UserDTO userDTO, Long idUser);
    void deleteById(Long id);
    UserDTO findByUsername (String username);
    UserDTO findByEmail(String email);
    boolean activateUser(String code);
}
