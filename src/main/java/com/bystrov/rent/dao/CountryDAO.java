package com.bystrov.rent.dao;

import com.bystrov.rent.domain.address.Country;

import java.util.List;

public interface CountryDAO {

    List<Country> findAll();
    void deleteById(Long idCountry);
    void save(Country country);
}
