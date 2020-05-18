package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.ImageDTO;
import com.bystrov.rent.domain.advertisement.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageDTOParser {

    public ImageDTO createImageDTOFromDomain(Image image){
        if (image == null){
            return null;
        }
        ImageDTO imageDTO = ImageDTO.builder()
                .idImage(image.getIdImage())
                .imagePath(image.getImagePath())
                .advertisement(image.getAdvertisement())
                .build();
        return imageDTO;
    }

    public Image createImageDomainFromDTO(ImageDTO imageDTO){
        if (imageDTO == null){
            return null;
        }
        Image image = Image.builder()
                .idImage(imageDTO.getIdImage())
                .imagePath(imageDTO.getImagePath())
                .advertisement(imageDTO.getAdvertisement())
                .build();
        return image;
    }
}
