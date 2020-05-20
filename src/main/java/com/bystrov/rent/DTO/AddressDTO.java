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

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Street cannot be empty")
    private String street;

    @NotBlank(message = "House cannot be empty")
    private Integer house;

    private Integer flat;
}
