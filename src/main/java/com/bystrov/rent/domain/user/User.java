package com.bystrov.rent.domain.user;

import com.bystrov.rent.domain.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class User {

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = true)
    private String login;

    @Column(nullable = true)
    private String password;

    @Column
    private String email;

    @Column
    private int age;

    @Column
    private long card;

    @Column
    private long paymentAccount;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn("ID_USER"))
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Advertisement> advertisementList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviewList;
}
