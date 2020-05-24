package com.bystrov.rent.service.impl;

import com.bystrov.rent.dao.AddressDAO;
import com.bystrov.rent.domain.address.Address;
import com.bystrov.rent.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressDAO addressDAO;

    @Autowired
    public AddressServiceImpl(AddressDAO addressDAO){ this.addressDAO = addressDAO; }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public void update(Address address) {

    }
}
