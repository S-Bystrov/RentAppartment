package com.bystrov.rent.dao;

import com.bystrov.rent.domain.advertisement.Advertisement;

import java.time.LocalDate;
import java.util.List;

public interface AdvertisementDAO {

    void save(Advertisement advertisement);
    Advertisement update(Advertisement advertisement);
    Advertisement findById(Long id);
    void deleteById(Long id);
    List<Advertisement> findAll();
    List<Advertisement> findAllByUserId(Long idUser);
    List<Advertisement> findByFilter(Long idCountry, String city, LocalDate filterArrivalDay, LocalDate filterDepartureDay);
}
