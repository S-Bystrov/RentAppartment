package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.ImageDTO;
import com.bystrov.rent.DTO.parser.ImageDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.ImageDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.advertisement.Image;
import com.bystrov.rent.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String standardImage = "standardImage.jpg";

    private final ImageDTOParser imageDTOParser;
    private final ImageDAO imageDAO;
    private final AdvertisementDAO advertisementDAO;

    @Autowired
    public ImageServiceImpl(ImageDTOParser imageDTOParser, ImageDAO imageDAO,
                            AdvertisementDAO advertisementDAO) {
        this.imageDTOParser = imageDTOParser;
        this.imageDAO = imageDAO;
        this.advertisementDAO = advertisementDAO;
    }

    @Transactional
    @Override
    public List<ImageDTO> findAllByIdAdvertisement(Long idAdvertisement) {
        List<Image> imageList = imageDAO.findAllByIdAdvertisement(idAdvertisement);
        if(imageList == null){
            return null;
        }
        List<ImageDTO> imageDTOList = new ArrayList<>();
        for (Image image : imageList) {
            imageDTOList.add(imageDTOParser.createImageDTOFromDomain(image));
        }
        return imageDTOList;
    }

    @Transactional
    @Override
    public List<ImageDTO> saveImage(List<String> imageList, Long idAdvertisement) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        List<Image> nameImageList = new ArrayList<>();
        List<ImageDTO> imageDTOList = new ArrayList<>();
        if(imageList != null) {
            for (String nameImage : imageList) {
                createImageDTOList(advertisement, nameImageList, imageDTOList, nameImage);
            }
        }
        else {
            createImageDTOList(advertisement, nameImageList, imageDTOList, standardImage);
        }
        return imageDTOList;
    }

    private void createImageDTOList(Advertisement advertisement, List<Image> nameImageList, List<ImageDTO> imageDTOList, String imageName) {
        Image image = new Image();
        image.setPath(imageName);
        nameImageList.add(image);
        image.setAdvertisement(advertisement);
        imageDAO.save(image);
        imageDTOList.add(imageDTOParser.createImageDTOFromDomain(image));
    }

    @Override
    public void deleteById(Long idImage) {

    }
}
