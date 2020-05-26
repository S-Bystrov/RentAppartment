package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.DTO.parser.UserDTOParser;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.domain.user.UserRole;
import com.bystrov.rent.service.MailSender;
import com.bystrov.rent.service.UserService;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
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
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String standardAvatarName = "standardAvatar.jpg";
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDTOParser userDTOParser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findById(Long id) {
        User user = userDAO.findById(id);
        if (user == null){
            throw new EntityNotFoundException("User not found!");
        }
        return userDTOParser.createUserDTOFromDomain(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDAO.findAll();
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
        userDTO.setActivate(false);
        userDTO.setAvatarName(standardAvatarName);
        userDTO.setActivationCode(UUID.randomUUID().toString());
        User user = userDTOParser.createUserDomainFromDTO(userDTO);
        logger.info("New user registered: " + userDTO.getUsername());
        userDAO.save(user);
        if(!StringUtils.isBlank(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                    "Welcome to RentAppartment. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode());

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return userDTO;
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO, Long idUser) {
        User user = userDAO.findById(idUser);
        if (user == null){
            throw new EntityNotFoundException("User not found!");
        }
        if(!StringUtils.isBlank(userDTO.getName())) {
            user.setName(userDTO.getName());
        }
        if(!StringUtils.isBlank(userDTO.getSurname())) {
            user.setSurname(userDTO.getSurname());
        }
        if(!StringUtils.isBlank(userDTO.getAvatarName())) {
            user.setAvatarName(userDTO.getAvatarName());
        }
        if(!StringUtils.isBlank(userDTO.getCard())) {
            user.setCard(userDTO.getCard());
        }
        if(!StringUtils.isBlank(userDTO.getPaymentAccount())){
            user.setPaymentAccount(userDTO.getPaymentAccount());
        }
        userDAO.update(user);
    }

    @Transactional
    @Override
    public boolean activateUser(String code) {
        User user = userDAO.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActivate(true);
        userDAO.update(user);
        return true;
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
    public UserDTO findByEmail(String email){
        User user = userDAO.findByEmail(email);
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

    @Override
    public boolean checkCard(User user) {
        User checkUser = userDAO.findById(user.getId());
        return !StringUtils.isBlank(checkUser.getCard());
    }
}
