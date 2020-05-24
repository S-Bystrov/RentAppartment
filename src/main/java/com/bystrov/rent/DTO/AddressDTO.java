package com.bystrov.rent.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long idAddress;

    private CountryDTO country = new CountryDTO();

    private String city;

    private String street;

    private String house;

    private String building;

    private String flat;
}
