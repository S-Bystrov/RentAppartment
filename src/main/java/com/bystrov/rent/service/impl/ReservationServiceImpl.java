package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.DTO.parser.ReservationDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.dao.UserDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.reservation.ReservationStatus;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.ReservationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO;
    private final ReservationDTOParser reservationDTOParser;
    private final UserDAO userDAO;
    private final AdvertisementDAO advertisementDAO;


    public ReservationServiceImpl(ReservationDAO reservationDAO,
                                  ReservationDTOParser reservationDTOParser,
                                  UserDAO userDAO,
                                  AdvertisementDAO advertisementDAO) {
        this.reservationDAO = reservationDAO;
        this.reservationDTOParser = reservationDTOParser;
        this.userDAO = userDAO;
        this.advertisementDAO = advertisementDAO;
    }

    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public List<ReservationDTO> getAll() {
        return null;
    }

    @Transactional
    @Override
    public ReservationDTO saveReservation(Long idAdvertisement, User authenticalUser, ReservationDTO reservationDTO) {
        Long idUser = authenticalUser.getId();
        User user = userDAO.findById(idUser);
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        advertisementDAO.update(advertisement);
        reservationDTO.setTotalCost(getTotalCost(reservationDTO, advertisement));
        reservationDTO.setAdvertisement(advertisement);
        reservationDTO.setUser(user);
        reservationDTO.setStatus(ReservationStatus.ACTIVE);
        reservationDAO.save(reservationDTOParser.createReservationDomainFromDTO(reservationDTO));
        return reservationDTO;
    }

    @Transactional
    @Override
    public List<ReservationDTO> getAllByUserId(Long idUser){
        List<Reservation> reservationList = reservationDAO.findAllByUserId(idUser);
        if(reservationList == null){
            return null;
        }
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDTOList.add(reservationDTOParser.createReservationDTOFromDomain(reservation));
        }
        return reservationDTOList;
    }

    @Override
    public void update(Reservation reservation) {

    }

    @Override
    public void deleteById(Long id) {

    }

    private Double getTotalCost(ReservationDTO reservationDTO, Advertisement advertisement) {
        Double price = Double.parseDouble(advertisement.getPrice());
        Long totalDay = ChronoUnit.DAYS.between(reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate());
        Double totalCost = price * totalDay.doubleValue();
        return totalCost;
    }
}
