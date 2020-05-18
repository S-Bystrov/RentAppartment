package com.bystrov.rent.domain.advertisement;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Image {

    @Id
    @Column(name = "ID_IMAGES")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;
}
