package com.bystrov.rent.validator;

import com.bystrov.rent.DTO.ReservationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return ReservationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReservationDTO reservation = (ReservationDTO) o;

        if(reservation.getArrivalDate() == null) {
            errors.rejectValue("arrivalDate", "error.arrivalDate.empty");
        } else {
            if (reservation.getArrivalDate().isBefore(LocalDate.now()) || reservation.getArrivalDate().isEqual(LocalDate.now())) {
                errors.rejectValue("arrivalDate", "error.arrivalDate");
            }
        }

        if(reservation.getDepartureDate() == null) {
            errors.rejectValue("departureDate", "error.departureDate.empty");
        } else {
            if (reservation.getDepartureDate().isBefore(LocalDate.now()) || reservation.getDepartureDate().isEqual(LocalDate.now())
                    || reservation.getDepartureDate().isBefore(reservation.getArrivalDate())
                    || reservation.getDepartureDate().isEqual(reservation.getArrivalDate())) {
                errors.rejectValue("departureDate", "error.departureDate");
            }
        }
    }
}
