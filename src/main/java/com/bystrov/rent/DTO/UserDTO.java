package com.bystrov.rent.DTO;

import com.bystrov.rent.DTO.transfer.CreateNew;
import com.bystrov.rent.DTO.transfer.Update;
import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    private long id;

    private UserType userType;

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    @Size(max = 30, message = "First name cannot be longer than 30 characters!", groups = Update.class)
    private String name;

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    private String surname;

    @Null(groups = Update.class)
    @NotNull(groups = CreateNew.class)
    @NotEmpty(message = "This field cannot be empty", groups = CreateNew.class)
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,15}$", message = "Login incorrect! Use 3-15 letters or numbers!", groups = CreateNew.class)
    private String login;

    @Null(groups = Update.class)
    @NotNull(groups = CreateNew.class)
    @NotEmpty(message = "This field cannot be empty", groups = CreateNew.class)
    private String password;

    @Null(groups = Update.class)
    @NotNull(groups = CreateNew.class)
    @NotEmpty(message = "This field cannot be empty", groups = CreateNew.class)
    private String confirmPassword;

    @Null(groups = Update.class)
    @NotNull(groups = CreateNew.class)
    @Email(groups = CreateNew.class)
    @NotEmpty(message = "This field cannot be empty", groups = CreateNew.class)
    private String email;

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    private int age;

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    private long card;

    @Null(groups = CreateNew.class)
    @NotNull(groups = Update.class)
    private long paymentAccount;

    private List<Advertisement> advertisementList;

    private List<Reservation> reservationList;

    private List<Review> reviewList;
}
