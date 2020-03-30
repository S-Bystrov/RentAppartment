package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.Advertisement;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementDAOImpl extends EntityDAO<Advertisement> implements AdvertisementDAO {

    public AdvertisementDAOImpl(){
        setEntityClass(Advertisement.class);
    }
}
