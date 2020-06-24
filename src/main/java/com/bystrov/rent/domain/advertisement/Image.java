package com.bystrov.rent.domain.advertisement;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table
public class Image {

    @Id
    @Column(name = "ID_IMAGES")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    private String path;

    @ManyToOne
    @JoinColumn(name = "ID_ADV")
    private Advertisement advertisement;
}
