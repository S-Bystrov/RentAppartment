package com.bystrov.rent.dao;


import com.bystrov.rent.domain.advertisement.Image;

import java.util.List;

public interface ImageDAO {

    void save(Image image);
    void deleteById(Long idImage);
    List<Image> findAllByIdAdvertisement(Long idAdvertisement);
}
