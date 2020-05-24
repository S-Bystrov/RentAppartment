package com.bystrov.rent.dao;

import com.bystrov.rent.domain.user.User;

import java.util.List;

public interface UserDAO {

    void save(User user);
    User update(User user);
    User findById(Long id);
    User findByUsername(String username);
    void deleteById(Long id);
    List<User> findAll();
    User findByEmail(String email);
    User findByActivationCode(String code);
}
