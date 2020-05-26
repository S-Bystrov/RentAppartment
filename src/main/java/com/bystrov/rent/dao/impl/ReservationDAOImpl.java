package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.reservation.ReservationStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ReservationDAOImpl extends EntityDAO<Reservation> implements ReservationDAO {

    public ReservationDAOImpl() {
        setEntityClass(Reservation.class);
    }

    @Override
    public Reservation findByStatus(String status) {
        Query query = em.createQuery("FROM Reservation WHERE status := paramStatus");
        query.setParameter("paramStatus", status);
        List<Reservation> result = (List<Reservation>) query.getResultList();
        if(result.size() != 0){
            return result.get(0);
        }
        return null;
    }
}
