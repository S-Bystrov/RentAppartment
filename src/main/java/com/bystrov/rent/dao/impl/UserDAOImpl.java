package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends EntityDAO<User> implements UserDAO {

    public UserDAOImpl(){
        setEntityClass(User.class);
    }

}
