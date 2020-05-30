package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.advertisement.Image;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertisementDTO {

    private Long idAdvertisement;

    private AddressDTO address = new AddressDTO();

    private String description;

    private List<Review> reviewList;

    private LocalDate date;

    private String price;

    private List<Image> images;

    private User user;

    private List<Reservation> reservation;
}
