package com.bystrov.rent.dao;

import com.bystrov.rent.domain.Reservation;

import java.util.List;

public interface ReservationDAO {

    void save(Reservation reservation);
    Reservation update(Reservation reservation);
    Reservation findById(Long id);
    void deleteById(Long id);
    List<Reservation> findAll();
    List<Reservation> findAllByUserId(Long idUser);
}
