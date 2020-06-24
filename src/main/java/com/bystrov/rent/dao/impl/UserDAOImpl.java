package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl extends EntityDAO<User> implements UserDAO {

    public UserDAOImpl() {
        setEntityClass(User.class);
    }

    public User findByUsername(String username) {
        Query query = em.createQuery("FROM User U WHERE U.username = :paramUsername");
        query.setParameter("paramUsername", username);
        List<User> result = (List<User>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }

    public User findByActivationCode(String code) {
        Query query = em.createQuery("FROM User U WHERE U.activationCode = :paramCode");
        query.setParameter("paramCode", code);
        List<User> result = (List<User>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }

    public User findByEmail(String email) {
        Query query = em.createQuery("FROM User U WHERE U.email = :paramEmail");
        query.setParameter("paramEmail", email);
        List<User> result = (List<User>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }
}
