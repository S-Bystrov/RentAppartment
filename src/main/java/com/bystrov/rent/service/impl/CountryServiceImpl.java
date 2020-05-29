package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.CountryDTO;
import com.bystrov.rent.DTO.parser.CountryDTOParser;
import com.bystrov.rent.dao.CountryDAO;
import com.bystrov.rent.domain.address.Country;
import com.bystrov.rent.service.CountryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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


    @Transactional
    @Override
    public void deleteById(Long id) {
        countryDAO.deleteById(id);
    }

    @Transactional
    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        Country country = countryDTOParser.createCountryDomainFromDTO(countryDTO);
        countryDAO.save(country);
        return countryDTO;
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

    @Transactional
    @Override
    public void update(CountryDTO countryDTO) {
        if(!StringUtils.isBlank(countryDTO.getCountryName())){
            if(countryDAO.findById(countryDTO.getIdCountry()) == null) {
                throw new EntityNotFoundException("Country no found!");
            } else {
                String country = countryDTO.getCountryName().substring(0,1).toUpperCase() +
                        countryDTO.getCountryName().substring(1).toLowerCase();
                countryDTO.setCountryName(country);
                countryDAO.update(countryDTOParser.createCountryDomainFromDTO(countryDTO));
            }
        }
    }
}
