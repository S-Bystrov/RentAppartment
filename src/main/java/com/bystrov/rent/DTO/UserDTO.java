package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
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

    @NotBlank(message = "Username cannot be empty")
    @Length(max = 16, message = "Username too long")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct(example@mail.com)", regexp =".+@.+\\..+")
    private String email;

    private String avatarName;

    private Long card;

    private Long paymentAccount;

    private List<Advertisement> advertisementList;

    private List<Reservation> reservationList;

    private List<Review> reviewList;
}
