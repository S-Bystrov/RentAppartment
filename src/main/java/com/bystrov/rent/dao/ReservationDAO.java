package com.bystrov.rent.dao;

import com.bystrov.rent.domain.reservation.Reservation;

import java.util.List;

public interface ReservationDAO {

    void save(Reservation reservation);
    Reservation update(Reservation reservation);
    Reservation findById(Long id);
    void delete(Reservation reservation);
    void deleteById(Long id);
    List<Reservation> findAll();
}
