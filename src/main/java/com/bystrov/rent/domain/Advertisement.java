package com.bystrov.rent.domain;

import com.bystrov.rent.domain.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Advertisement {

    @Id
    @Column(name = "ID_ADV")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAdvertisement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADRESS")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private List<Review> reviewList;

    @Column(nullable = true)
    private int data;

    @Column(nullable = true)
    private double price;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RSRVT")
    private Reservation reservation;

}
