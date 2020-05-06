package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.parser.AdvertisementDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementDAO advertisementDAO;

    private AdvertisementDTOParser advertisementDTOParser;

    public AdvertisementServiceImpl(AdvertisementDAO advertisementDAO,
                                    AdvertisementDTOParser advertisementDTOParser) {
        this.advertisementDAO = advertisementDAO;
        this.advertisementDTOParser = advertisementDTOParser;
    }

    @Override
    public Advertisement findById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public List<AdvertisementDTO> getAll() {
        List<Advertisement> advertisements = advertisementDAO.getAll();
        if(advertisements == null){
            return null;
        }
        List<AdvertisementDTO> advertisementDTOList = new ArrayList<>();
        for (Advertisement advertisement : advertisements) {
            advertisementDTOList.add(advertisementDTOParser.createAdvertDTOFromDomain(advertisement));
        }
        return advertisementDTOList;
    }

    @Transactional
    @Override
    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO) {
        if(advertisementDTO == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        Advertisement advertisement = advertisementDTOParser.createAdvertDomainFromDTO(advertisementDTO);
        advertisementDAO.save(advertisement);
        return advertisementDTO;
    }

    @Override
    public void update(AdvertisementDTO advertisementDTO) {

    }

    @Override
    public void delete(Advertisement advertisement) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
