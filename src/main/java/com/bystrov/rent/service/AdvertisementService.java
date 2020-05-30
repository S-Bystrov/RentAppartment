package com.bystrov.rent.service;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

public interface AdvertisementService {
    AdvertisementDTO findById(Long id);
    AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO);
    void update(Long idAdvertisement);
    void deleteById(Long id);
    Page<AdvertisementDTO> findPaginatedByUserId(Pageable pageable, Long userId);
    boolean checkUser(Long idAdvertisement, User user);
    boolean checkByDate(ReservationDTO reservationDTO);
    Page<AdvertisementDTO> findPaginated(Pageable pageable);
    Page<AdvertisementDTO> findPaginatedByFilter(Pageable pageable,
                                                 Long filterCountry,
                                                 String filterCity,
                                                 String arrivalDate,
                                                 String departureDay) throws ParseException;
}
