package com.bystrov.rent.dao;

import com.bystrov.rent.domain.Address;


public interface AddressDAO {

    void save(Address address);
    Address update(Address address);
    Address findById(Long id);
    void delete(Address address);
    void deleteById(Long id);
}
