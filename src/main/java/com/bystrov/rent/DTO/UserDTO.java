package com.bystrov.rent.DTO;

import com.bystrov.rent.DTO.transfer.CreateNew;
import com.bystrov.rent.DTO.transfer.Update;
import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
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


    private long id;


    private Set<UserRole> roles;


    private String name;


    private String surname;


    private String username;


    private String password;


    private String confirmPassword;


    private String email;


    private int age;


    private long card;


    private long paymentAccount;

    private List<Advertisement> advertisementList;

    private List<Reservation> reservationList;

    private List<Review> reviewList;
}
