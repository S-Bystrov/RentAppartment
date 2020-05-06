package com.bystrov.rent.service;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.Advertisement;

import java.util.List;

public interface AdvertisementService {
    Advertisement findById(Long id);
    List<AdvertisementDTO> getAll();
    AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);
    void update(AdvertisementDTO advertisementDTO);
    void delete(Advertisement advertisement);
    void deleteById(Long id);
}
