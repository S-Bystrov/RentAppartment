package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ReviewDAO;
import com.bystrov.rent.domain.Review;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import java.util.List;

@Repository
public class ReviewDAOImpl extends EntityDAO<Review> implements ReviewDAO {

    public ReviewDAOImpl() {
        setEntityClass(Review.class);
    }

    @Override
    public List<Review> findAllByIdAdvertisement(Long idAdvertisement) {
        Query query = em.createQuery("FROM Review WHERE ID_ADV =: paramIdAdv");
        query.setParameter("paramIdAdv", idAdvertisement);
        List<Review> reviewList = (List<Review>) query.getResultList();
        if(reviewList.size() == 0) {
            return null;
        }
        return reviewList;
    }
}
