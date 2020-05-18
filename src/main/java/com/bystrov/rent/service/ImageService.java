package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ImageDTO;

import java.util.List;

public interface ImageService {

    List<ImageDTO> findAllByIdAdvertisement(Long idAdvertisement);
    ImageDTO saveImage(String nameImage, Long idAdvertisement);
    void deleteById(Long idImage);
}
