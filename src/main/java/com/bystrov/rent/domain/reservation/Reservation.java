package com.bystrov.rent.domain.reservation;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RSRVT")
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;

    private String data;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
