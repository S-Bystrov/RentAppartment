package com.bystrov.rent.domain.reservation;

import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RSRVT")
    private long idReservation;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;

    @Column(nullable = true)
    private int data;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
