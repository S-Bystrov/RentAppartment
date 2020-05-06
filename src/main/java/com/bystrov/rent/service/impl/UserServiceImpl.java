package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.DTO.parser.UserDTOParser;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.domain.user.UserRole;
import com.bystrov.rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDTOParser userDTOParser;

    @Autowired
    private PasswordEncoder passwordEncoder;

/*    private final UserDAOImpl userDAO;

    private final UserDTOParser userDTOParser;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO, UserDTOParser userDTOParser, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userDTOParser = userDTOParser;
        this.passwordEncoder = passwordEncoder;
    }*/

    @Transactional
    @Override
    public UserDTO findById(Long id) {
        User user = userDAO.findById(id);
        if (user == null){
            throw new EntityNotFoundException("User not found!");
        }
        return userDTOParser.createUserDTOFromDomain(user);
    }

    @Transactional
    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(userDTOParser.createUserDTOFromDomain(user));
        }
        return userDTOList;
    }

    @Transactional
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRoles(Collections.singleton(UserRole.USER));
        User user = userDTOParser.createUserDomainFromDTO(userDTO);
        userDAO.save(user);
        return userDTO;
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO, String username) {
        User user = userDAO.findByUsername(username);
        if (user == null){
            throw new EntityNotFoundException("User not found!");
        }
        if(userDTO.getAge() != 0) {
            user.setAge(userDTO.getAge());
        }
        if(userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if(userDTO.getSurname() != null) {
            user.setSurname(userDTO.getSurname());
        }
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        UserDTO userDTO = userDTOParser.createUserDTOFromDomain(user);
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
