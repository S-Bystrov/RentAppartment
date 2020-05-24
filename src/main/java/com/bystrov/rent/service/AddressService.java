package com.bystrov.rent.service;

import com.bystrov.rent.domain.address.Address;

public interface AddressService {
    Address findById(Long id);
    void update(Address address);
}
