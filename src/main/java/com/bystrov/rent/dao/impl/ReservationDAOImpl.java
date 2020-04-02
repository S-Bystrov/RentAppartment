package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.reservation.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAOImpl extends EntityDAO<Reservation> implements ReservationDAO {

    public ReservationDAOImpl() {
        setEntityClass(Reservation.class);
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }
}
