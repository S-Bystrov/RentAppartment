package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends EntityDAO<User> implements UserDAO {

    public UserDAOImpl() {
        setEntityClass(User.class);
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
