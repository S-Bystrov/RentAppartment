package com.bystrov.rent.dao;

import com.bystrov.rent.domain.address.Address;


public interface AddressDAO {

    void save(Address address);
    Address update(Address address);
    Address findById(Long id);
    void deleteById(Long id);
}
