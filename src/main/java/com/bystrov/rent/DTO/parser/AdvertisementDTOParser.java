package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementDTOParser {

    private final AddressDTOParser addressDTOParser;

    public AdvertisementDTOParser(AddressDTOParser addressDTOParser) {
        this.addressDTOParser = addressDTOParser;
    }

    public AdvertisementDTO createAdvertDTOFromDomain(Advertisement advertisement){
        if (advertisement == null){
            return null;
        }

        AdvertisementDTO advertisementDTO = AdvertisementDTO.builder()
                .idAdvertisement(advertisement.getIdAdvertisement())
                .date(advertisement.getDate())
                .description(advertisement.getDescription())
                .rating(advertisement.getRating())
                .address(addressDTOParser.createAddressDTOFromDomain(advertisement.getAddress()))
                .price(advertisement.getPrice())
                .user(advertisement.getUser())
                .reservations(advertisement.getReservations())
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
                .date(advertisementDTO.getDate())
                .description(advertisementDTO.getDescription())
                .rating(advertisementDTO.getRating())
                .address(addressDTOParser.createAddressDomainFromDTO(advertisementDTO.getAddress()))
                .price(advertisementDTO.getPrice())
                .user(advertisementDTO.getUser())
                .reservations(advertisementDTO.getReservations())
                .images(advertisementDTO.getImages())
                .build();
        return advertisement;
    }
}
