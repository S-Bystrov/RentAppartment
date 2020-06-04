package com.bystrov.rent.validator;

import com.bystrov.rent.DTO.ReservationDateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class ReservationDateValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return ReservationDateDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReservationDateDTO reservationDate = (ReservationDateDTO) o;

        if(reservationDate.getArrivalDate() == null) {
            errors.rejectValue("arrivalDate", "error.arrivalDate.empty");
        } else {
            if (reservationDate.getArrivalDate().isBefore(LocalDate.now()) || reservationDate.getArrivalDate().isEqual(LocalDate.now())) {
                errors.rejectValue("arrivalDate", "error.arrivalDate");
            }
        }

        if(reservationDate.getDepartureDate() == null) {
            errors.rejectValue("departureDate", "error.departureDate.empty");
        } else {
            if (reservationDate.getDepartureDate().isBefore(LocalDate.now()) || reservationDate.getDepartureDate().isEqual(LocalDate.now())
                    || reservationDate.getDepartureDate().isBefore(reservationDate.getArrivalDate())
                    || reservationDate.getDepartureDate().isEqual(reservationDate.getArrivalDate())) {
                errors.rejectValue("departureDate", "error.departureDate");
            }
        }
    }
}
