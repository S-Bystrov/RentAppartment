package com.bystrov.rent.service;

import com.bystrov.rent.domain.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> getAll();
    User saveUser(User user);
    void update(User user);
    void delete(User user);
    void deleteById(Long id);
    User findByLogin (String login);
}
