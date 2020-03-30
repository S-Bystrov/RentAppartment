package com.bystrov.rent.service.impl;

import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO) { this.reservationDAO = reservationDAO; }

    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return null;
    }

    @Override
    public void update(Reservation reservation) {

    }

    @Override
    public void delete(Reservation reservation) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
