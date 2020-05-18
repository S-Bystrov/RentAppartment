package com.bystrov.rent.domain.Address;

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

    private String country;

    private String city;

    private String street;

    private Integer house;

    private Integer flat;

    @OneToOne(mappedBy = "address")
    private Advertisement advertisement;
}
