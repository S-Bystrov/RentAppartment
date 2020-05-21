package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.AddressDTO;
import com.bystrov.rent.domain.address.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressDTOParser {

    private final CountryDTOParser countryDTOParser;

    public AddressDTOParser(CountryDTOParser countryDTOParser) {
        this.countryDTOParser = countryDTOParser;
    }

    public AddressDTO createAddressDTOFromDomain(Address address){
        if (address == null){
            return null;
        }

        AddressDTO addressDTO = AddressDTO.builder()
                .idAddress(address.getIdAddress())
                .country(countryDTOParser.createCountryDTOFromDomain(address.getCountry()))
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .flat(address.getFlat())
                .build();
        return addressDTO;
    }

    public Address createAddressDomainFromDTO(AddressDTO addressDTO){
        if (addressDTO == null){
            return null;
        }

        Address address = Address.builder()
                .idAddress(addressDTO.getIdAddress())
                .country(countryDTOParser.createCountryDomainFromDTO(addressDTO.getCountry()))
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .house(addressDTO.getHouse())
                .flat(addressDTO.getFlat())
                .build();
        return address;
    }
}
