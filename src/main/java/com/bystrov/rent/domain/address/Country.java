package com.bystrov.rent.domain.address;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COUNTRY")
    private Long idCountry;

    private String countryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country", orphanRemoval = true)
    private List<Address> address;
}
