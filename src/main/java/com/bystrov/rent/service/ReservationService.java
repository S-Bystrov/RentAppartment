package com.bystrov.rent.service;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.User;

import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);
    List<ReservationDTO> getAll();
    ReservationDTO saveReservation(Long idAdvertisement, User user);
    void update(Reservation reservation);
    void delete(Reservation reservation);
    void deleteById(Long id);
    List<ReservationDTO> getAllByUserId(Long idUser);
}
