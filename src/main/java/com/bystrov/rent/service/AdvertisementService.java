package com.bystrov.rent.service;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;

import java.util.List;

public interface AdvertisementService {
    AdvertisementDTO findById(Long id);
    List<AdvertisementDTO> getAll();
    AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);
    void update(Long idAdvertisement, String status);
    void deleteById(Long id);
    List<AdvertisementDTO> getAllByUserId(long userId);
    List<AdvertisementDTO> findByFilter(Long filterCountry, String filterCity);
    List<AdvertisementDTO> getAllFree();
    boolean checkUser(Long idAdvertisement, User user);
}
