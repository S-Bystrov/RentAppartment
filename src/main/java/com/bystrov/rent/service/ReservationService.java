package com.bystrov.rent.service;

import com.bystrov.rent.domain.reservation.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);
    List<Reservation> getAll();
    Reservation saveReservation(Reservation reservation);
    void update(Reservation reservation);
    void delete(Reservation reservation);
    void deleteById(Long id);
}
