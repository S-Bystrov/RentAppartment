package com.bystrov.rent.domain.user;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Review;
import com.bystrov.rent.domain.reservation.Reservation;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class User implements UserDetails {

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "user_name")
    private String username;

    private String password;

    private String email;

    private String card;

    private String paymentAccount;

    private String avatarName;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "ID_USER"))
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Advertisement> advertisementList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviewList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
