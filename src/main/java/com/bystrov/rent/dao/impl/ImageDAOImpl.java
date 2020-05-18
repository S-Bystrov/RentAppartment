package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ImageDAO;
import com.bystrov.rent.domain.advertisement.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ImageDAOImpl extends EntityDAO<Image> implements ImageDAO {

    public ImageDAOImpl() {
        setEntityClass(Image.class);
    }

    @Override
    public List<Image> findAllByIdAdvertisement(Long idAdvertisement) {
        Query query = em.createQuery("FROM Image WHERE ID_ADV =: paramIdAdv");
        query.setParameter("paramIdAdv", idAdvertisement);
        List<Image> imageList = (List<Image>) query.getResultList();
        if(imageList.size() == 0) {
            return null;
        }
        return imageList;
    }
}
