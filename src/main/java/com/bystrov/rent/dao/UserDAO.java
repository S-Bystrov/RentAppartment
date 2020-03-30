package com.bystrov.rent.dao;

import com.bystrov.rent.domain.User;

import java.util.List;

public interface UserDAO {

    void save(User user);
    User update(User user);
    User findById(Long id);
    User findByLogin(String login);
    void delete(User user);
    void deleteById(Long id);
    List<User> getAll();
}
