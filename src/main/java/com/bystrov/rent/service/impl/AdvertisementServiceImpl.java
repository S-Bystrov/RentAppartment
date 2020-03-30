package com.bystrov.rent.service.impl;

import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementDAO advertisementDAO;

    @Override
    public Advertisement findById(Long id) {
        return null;
    }

    @Override
    public List<Advertisement> getAll() {
        return null;
    }

    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement) {
        return null;
    }

    @Override
    public void update(Advertisement advertisement) {

    }

    @Override
    public void delete(Advertisement advertisement) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
