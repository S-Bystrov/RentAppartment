package com.bystrov.rent.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private long idAddress;

    private String country;

    private String city;

    private String street;

    private int house;

    private int flat;
}
