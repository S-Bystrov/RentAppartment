package com.bystrov.rent.domain.address;

import com.bystrov.rent.domain.advertisement.Advertisement;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS")
    private Long idAddress;

    @ManyToOne
    @JoinColumn(name = "ID_COUNTRY")
    private Country country;

    private String city;

    private String street;

    private String house;

    private String building;

    private String flat;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Advertisement advertisement;
}
