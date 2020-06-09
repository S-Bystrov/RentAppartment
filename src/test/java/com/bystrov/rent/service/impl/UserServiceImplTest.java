package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.UserDTO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.domain.user.UserRole;
import com.bystrov.rent.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void saveUser() {
        UserDTO userDTO = new UserDTO();
        UserDTO checkSave = userService.saveUser(userDTO);

        Assert.assertNotNull(checkSave);
        Assert.assertNotNull(userDTO.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(userDTO.getRoles()).matches(Collections.singleton(UserRole.USER)));

    }

    @Test
    public void saveUserFailTest() {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername("Test");

        Mockito.doReturn(new User())
                .when(userDAO)
                .findByUsername("Test");

        UserDTO checkSave = userService.saveUser(userDTO);
        Assert.assertNull(checkSave);
    }

    @Test
    public void activateUser() {
        User user = new User();

        user.setActivationCode("activate test");

        Mockito.doReturn(user)
                .when(userDAO)
                .findByActivationCode("activate");

        boolean isUserActivate = userService.activateUser("activate");

        Assert.assertTrue(isUserActivate);
        Assert.assertNull(user.getActivationCode());

        Mockito.verify(userDAO, Mockito.times(1)).update(user);

    }

}