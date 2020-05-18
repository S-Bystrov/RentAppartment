package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementDTOParser {

    public AdvertisementDTO createAdvertDTOFromDomain(Advertisement advertisement){
        if (advertisement == null){
            return null;
        }

        AdvertisementDTO advertisementDTO = AdvertisementDTO.builder()
                .idAdvertisement(advertisement.getIdAdvertisement())
                .data(advertisement.getData())
                .description(advertisement.getDescription())
                .address(advertisement.getAddress())
                .price(advertisement.getPrice())
                .user(advertisement.getUser())
                .reservation(advertisement.getReservation())
                .images(advertisement.getImages())
                .build();
        return advertisementDTO;
    }

    public Advertisement createAdvertDomainFromDTO(AdvertisementDTO advertisementDTO){
        if (advertisementDTO == null){
            return null;
        }

        Advertisement advertisement = Advertisement.builder()
                .idAdvertisement(advertisementDTO.getIdAdvertisement())
                .data(advertisementDTO.getData())
                .description(advertisementDTO.getDescription())
                .address(advertisementDTO.getAddress())
                .price(advertisementDTO.getPrice())
                .user(advertisementDTO.getUser())
                .reservation(advertisementDTO.getReservation())
                .images(advertisementDTO.getImages())
                .build();
        return advertisement;
    }
}
