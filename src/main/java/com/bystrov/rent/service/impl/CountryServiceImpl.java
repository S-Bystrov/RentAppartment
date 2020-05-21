package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.DTO.parser.CountryDTOParser;
import com.bystrov.rent.dao.CountryDAO;
import com.bystrov.rent.domain.address.Country;
import com.bystrov.rent.service.CountryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDAO countryDAO;
    private final CountryDTOParser countryDTOParser;


    public CountryServiceImpl(CountryDAO countryDAO, CountryDTOParser countryDTOParser) {
        this.countryDAO = countryDAO;
        this.countryDTOParser = countryDTOParser;
    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public CountryDTO saveAddress(CountryDTO countryDTO) {
        return null;
    }

    @Transactional
    @Override
    public List<CountryDTO> getAll() {
        List<Country> countryList = countryDAO.findAll();
        if(countryList == null) {
            return null;
        } else {
            List<CountryDTO> countryDTOList = new ArrayList<>();
            for (Country country : countryList) {
                countryDTOList.add(countryDTOParser.createCountryDTOFromDomain(country));
            }
            return countryDTOList;
        }
    }
}
