package com.bystrov.rent.DTO;

import com.bystrov.rent.domain.advertisement.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {

    private Long idImage;

    private String path;

    private Advertisement advertisement;
}
