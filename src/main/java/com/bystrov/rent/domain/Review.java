package com.bystrov.rent.domain;

import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RVW")
    private Long idReview;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column
    private String review;

    @Column
    private String data;

    @Column
    private Double evaluation;

    @ManyToOne
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;
}
