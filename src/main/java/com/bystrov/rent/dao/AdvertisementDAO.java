package com.bystrov.rent.dao;

import com.bystrov.rent.domain.advertisement.Advertisement;

import java.util.List;

public interface AdvertisementDAO {

    void save(Advertisement advertisement);
    Advertisement update(Advertisement advertisement);
    Advertisement findById(Long id);
    void delete(Advertisement advertisement);
    void deleteById(Long id);
    List<Advertisement> findAll();
    List<Advertisement> findAllByUserId(long idUser);
}
