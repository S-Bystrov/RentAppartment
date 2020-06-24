package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.Reservation;
import com.bystrov.rent.domain.user.User;

import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);
    List<ReservationDTO> getAll();
    ReservationDTO saveReservation(Long idAdvertisement, User user, ReservationDTO reservationDTO);
    void update(Reservation reservation);
    void deleteById(Long id);
    List<ReservationDTO> getAllByUserId(Long idUser);
}
