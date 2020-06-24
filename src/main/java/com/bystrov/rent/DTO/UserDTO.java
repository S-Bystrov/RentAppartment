package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.Reservation;
import com.bystrov.rent.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {


    private Long id;

    private Set<UserRole> roles;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private String activationCode;

    private boolean activate;

    private String avatarName;

    private String card;

    private String paymentAccount;

    private List<Advertisement> advertisementList;

    private List<Reservation> reservationList;

    private List<Review> reviewList;
}
