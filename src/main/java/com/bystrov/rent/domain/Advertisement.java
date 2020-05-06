package com.bystrov.rent.domain;

import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.util.LocalDateAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private List<Review> reviewList;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate data;

    @Column
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RSRVT")
    private Reservation reservation;

}
