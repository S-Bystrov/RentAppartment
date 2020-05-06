package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.Address;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertisementDTO {

    private long idAdvertisement;

    private Address address;

    private String description;

    private List<Review> reviewList;

    private int data;

    private double price;

    private User user;

    private Reservation reservation;
}
