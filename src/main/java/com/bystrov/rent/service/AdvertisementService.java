package com.bystrov.rent.service;

import com.bystrov.rent.domain.Advertisement;

import java.util.List;

public interface AdvertisementService {
    Advertisement findById(Long id);
    List<Advertisement> getAll();
    Advertisement saveAdvertisement(Advertisement advertisement);
    void update(Advertisement advertisement);
    void delete(Advertisement advertisement);
    void deleteById(Long id);
}
