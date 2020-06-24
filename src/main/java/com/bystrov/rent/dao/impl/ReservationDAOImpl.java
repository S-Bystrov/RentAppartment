package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDAOImpl extends EntityDAO<Reservation> implements ReservationDAO {

    public ReservationDAOImpl() {
        setEntityClass(Reservation.class);
    }
}
