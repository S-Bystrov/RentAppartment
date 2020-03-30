package com.bystrov.rent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RVW")
    private int idReview;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;

    @Column(nullable = true)
    private String review;

    @Column(nullable = true)
    private int data;

    @Column(nullable = true)
    private double evaluation;

    @ManyToOne
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;
}
