package com.bystrov.rent.service;

import com.bystrov.rent.domain.Address.Address;

public interface AddressService {
    Address findById(Long id);
    Address saveAddress(Address address);
    void update(Address address);
    void delete(Address address);
    void deleteById(Long id);
}
