package com.bystrov.rent.domain.advertisement;

import com.bystrov.rent.domain.address.Address;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.util.LocalDateAttributeConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long idAdvertisement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADDRESS")
    private Address address;

    private String description;

    private Double rating;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private List<Review> reviewList;

    @Convert(converter = LocalDateAttributeConverter.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private String price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private List<Image> images;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private List<Reservation> reservation;

}
