package com.bystrov.rent.service;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);
    Page<UserDTO> findPaginated(Pageable pageable);
    UserDTO saveUser(UserDTO userDTO);
    void update(UserDTO userDTO, Long idUser);
    void deleteById(Long id);
    UserDTO findByUsername (String username);
    UserDTO findByEmail(String email);
    boolean activateUser(String code);
    boolean checkCard(User user);
}
