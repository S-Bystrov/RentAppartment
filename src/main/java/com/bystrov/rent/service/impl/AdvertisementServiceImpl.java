package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.parser.AdvertisementDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementDAO advertisementDAO;

    private final AdvertisementDTOParser advertisementDTOParser;

    public AdvertisementServiceImpl(AdvertisementDAO advertisementDAO,
                                    AdvertisementDTOParser advertisementDTOParser) {
        this.advertisementDAO = advertisementDAO;
        this.advertisementDTOParser = advertisementDTOParser;
    }

    @Transactional
    @Override
    public AdvertisementDTO findById(Long id) {
        Advertisement advertisement = advertisementDAO.findById(id);
        if(advertisement == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        AdvertisementDTO advertisementDTO = advertisementDTOParser.createAdvertDTOFromDomain(advertisement);
        return advertisementDTO;
    }

    @Transactional
    @Override
    public List<AdvertisementDTO> getAll() {
        List<Advertisement> advertisements = advertisementDAO.findAll();
        return getAdvertisementList(advertisements);
    }


    @Transactional
    @Override
    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO) {
        if(advertisementDTO == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        Advertisement advertisement = advertisementDTOParser.createAdvertDomainFromDTO(advertisementDTO);
        advertisement.setData(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        advertisementDAO.save(advertisement);
        return advertisementDTOParser.createAdvertDTOFromDomain(advertisement);
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

    @Transactional
    @Override
    public List<AdvertisementDTO> getAllByUserId(long userId) {
        List<Advertisement> advertisements = advertisementDAO.findAllByUserId(userId);
        return getAdvertisementList(advertisements);
    }

    @Transactional
    @Override
    public String findUsernameByIdAdvertisement(Long idAdvertisement) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        User user = advertisement.getUser();
        String username = user.getUsername();
        return username;
    }


    private List<AdvertisementDTO> getAdvertisementList(List<Advertisement> advertisements) {
        if (advertisements == null) {
            return null;
        } else {
            List<AdvertisementDTO> advertisementDTOList = new ArrayList<>();
            for (Advertisement advertisement : advertisements) {
                advertisementDTOList.add(advertisementDTOParser.createAdvertDTOFromDomain(advertisement));
            }
            return advertisementDTOList;
        }
    }

/*    @Override
    public List<AdvertisementDTO> findByFilter(String filterCountry, String filterCity) {
        List<AdvertisementDTO> advertisementList = new ArrayList<>();
        return advertisementList;
    }*/
}
