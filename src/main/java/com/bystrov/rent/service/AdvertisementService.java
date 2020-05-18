package com.bystrov.rent.service;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.advertisement.Advertisement;

import java.util.List;

public interface AdvertisementService {
    AdvertisementDTO findById(Long id);
    List<AdvertisementDTO> getAll();
    AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);
    void update(AdvertisementDTO advertisementDTO);
    void delete(Advertisement advertisement);
    void deleteById(Long id);
    List<AdvertisementDTO> getAllByUserId(long userId);
    String findUsernameByIdAdvertisement(Long idAdvertisement);
  /*  List<AdvertisementDTO> findByFilter(String filterCountry, String filterCity);*/
}
