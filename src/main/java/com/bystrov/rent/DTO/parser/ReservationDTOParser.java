package com.bystrov.rent.DTO.parser;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.domain.reservation.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationDTOParser {

    public ReservationDTO createReservationDTOFromDomain(Reservation reservation){
        if (reservation == null){
            return null;
        }
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .idReservation(reservation.getIdReservation())
                .user(reservation.getUser())
                .advertisement(reservation.getAdvertisement())
                .arrivalDate(reservation.getArrivalDate())
                .departureDate(reservation.getDepartureDate())
                .totalCost(reservation.getTotalCost())
                .status(reservation.getStatus())
                .build();
        return reservationDTO;
    }

    public Reservation createReservationDomainFromDTO(ReservationDTO reservationDTO){
        if (reservationDTO == null){
            return null;
        }
        Reservation reservation = Reservation.builder()
                .idReservation(reservationDTO.getIdReservation())
                .user(reservationDTO.getUser())
                .advertisement(reservationDTO.getAdvertisement())
                .arrivalDate(reservationDTO.getArrivalDate())
                .departureDate(reservationDTO.getDepartureDate())
                .totalCost(reservationDTO.getTotalCost())
                .status(reservationDTO.getStatus())
                .build();
        return reservation;
    }
}
