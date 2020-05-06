package com.bystrov.rent.domain;

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
    @Column(name = "ID_ADRESS")
    private long idAddress;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private int house;

    @Column
    private int flat;

    @OneToOne(mappedBy = "address")
    private Advertisement advertisement;
}
