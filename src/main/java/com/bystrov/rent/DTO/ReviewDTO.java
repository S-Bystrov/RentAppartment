package com.bystrov.rent.DTO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

        private Long idReview;

        private User user;

        private String review;

        private String data;

        private Double evaluation;

        private Advertisement advertisement;
}

