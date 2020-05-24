package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    private String avatarName;

    @Pattern(regexp = "^[0-9]{13,16}$", message = "Bank card number must be 16 characters long!")
    private String card;

    @Pattern(regexp = "^[0-9]{13,16}$", message = "Account number must be 16 characters long!")
    private String paymentAccount;

    private List<Advertisement> advertisementList;

    private List<Reservation> reservationList;

    private List<Review> reviewList;
}
