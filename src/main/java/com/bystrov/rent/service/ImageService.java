package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ImageDTO;

import java.util.List;

public interface ImageService {

    List<ImageDTO> findAllByIdAdvertisement(Long idAdvertisement);
    List<ImageDTO> saveImage(List<String> imageList, Long idAdvertisement);
    void deleteById(Long idImage);
}
