package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ReviewDAO;
import com.bystrov.rent.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAOImpl extends EntityDAO<Review> implements ReviewDAO {

    public ReviewDAOImpl() {
        setEntityClass(Review.class);
    }

    @Override
    public List<Review> getAll() {
        return null;
    }
}
