package com.bystrov.rent.domain;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.util.LocalDateAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate arrivalDate;

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate departureDate;

    private Double totalCost;

}
