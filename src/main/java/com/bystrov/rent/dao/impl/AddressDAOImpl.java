package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.AddressDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.Address.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl extends EntityDAO<Address> implements AddressDAO {

    public AddressDAOImpl(){
        setEntityClass(Address.class);
    }


}
