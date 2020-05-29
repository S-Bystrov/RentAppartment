package com.bystrov.rent.service;

import com.bystrov.rent.DTO.CountryDTO;

import java.util.List;

public interface CountryService {

    void deleteById(Long id);
    CountryDTO save(CountryDTO countryDTO);
    List<CountryDTO> getAll();
    void update(CountryDTO countryDTO);
}
