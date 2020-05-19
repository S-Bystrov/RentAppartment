package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.domain.Address.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryDTOParser {

    public CountryDTO createCountryDTOFromDomain(Country country){
        if (country == null){
            return null;
        }

        CountryDTO countryDTO = CountryDTO.builder()
                .idCountry(country.getIdCountry())
                .countryName(country.getCountryName())
                .build();
        return countryDTO;
    }

    public Country createCountryDomainFromDTO(CountryDTO countryDTO){
        if (countryDTO == null){
            return null;
        }

        Country country = Country.builder()
                .idCountry(countryDTO.getIdCountry())
                .countryName(countryDTO.getCountryName())
                .build();
        return country;
    }
}
