package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.CountryDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.address.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl extends EntityDAO<Country> implements CountryDAO {

    public CountryDAOImpl() { setEntityClass(Country.class);}
}
