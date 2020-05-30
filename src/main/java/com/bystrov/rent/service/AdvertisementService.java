package com.bystrov.rent.service;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;

import java.text.ParseException;
import java.util.List;

public interface AdvertisementService {
    AdvertisementDTO findById(Long id);
    List<AdvertisementDTO> getAll();
    AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);
    void update(Long idAdvertisement);
    void deleteById(Long id);
    List<AdvertisementDTO> getAllByUserId(long userId);
    List<AdvertisementDTO> findByFilter(Long filterCountry, String filterCity, String arrivalDate, String departureDay) throws ParseException;
    boolean checkUser(Long idAdvertisement, User user);
    boolean checkBydate(ReservationDTO reservationDTO);
}
