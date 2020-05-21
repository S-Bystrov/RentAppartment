package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.reservation.ReservationStatus;
import com.bystrov.rent.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long idReservation;

    private User user;

    private Advertisement advertisement;

    private String data;

    private ReservationStatus status;
}
