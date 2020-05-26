package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.parser.AdvertisementDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.advertisement.Status;
import com.bystrov.rent.domain.reservation.Reservation;
import com.bystrov.rent.domain.reservation.ReservationStatus;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementDAO advertisementDAO;

    private final ReservationDAO reservationDAO;

    private final AdvertisementDTOParser advertisementDTOParser;

    public AdvertisementServiceImpl(AdvertisementDAO advertisementDAO,
                                    AdvertisementDTOParser advertisementDTOParser,
                                    ReservationDAO reservationDAO) {
        this.advertisementDAO = advertisementDAO;
        this.advertisementDTOParser = advertisementDTOParser;
        this.reservationDAO = reservationDAO;
    }

    @Transactional
    @Override
    public AdvertisementDTO findById(Long id) {
        Advertisement advertisement = advertisementDAO.findById(id);
        if(advertisement == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        AdvertisementDTO advertisementDTO = advertisementDTOParser.createAdvertDTOFromDomain(advertisement);
        return advertisementDTO;
    }

    @Transactional
    @Override
    public List<AdvertisementDTO> getAll() {
        List<Advertisement> advertisements = advertisementDAO.findAll();
        return getAdvertisementList(advertisements);
    }


    @Transactional
    @Override
    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO) {
        if(advertisementDTO == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        Advertisement advertisement = advertisementDTOParser.createAdvertDomainFromDTO(advertisementDTO);
        advertisement.getAddress().setCity(advertisementDTO.getAddress().getCity().toLowerCase());
        advertisement.setData(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        advertisement.setStatus(Status.FREE);
        advertisementDAO.save(advertisement);
        return advertisementDTOParser.createAdvertDTOFromDomain(advertisement);
    }

    @Transactional
    @Override
    public void update(Long idAdvertisement, String status) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        if(advertisement == null){
            throw new EntityNotFoundException("Advertisement not found!");
        }
        String oldStatus = advertisement.getStatus().toString();
        if(status != oldStatus){
            if(status.equals("FREE")){
                Reservation reservation = reservationDAO.findByStatus("ACTIVE");
                reservation.setStatus(ReservationStatus.CLOSE);
                reservationDAO.update(reservation);
                advertisement.setStatus(Status.FREE);
            } else {
                advertisement.setStatus(Status.BOOKED);
            }
        }
        advertisement.setStatus(Status.valueOf(status));
        advertisementDAO.update(advertisement);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        advertisementDAO.deleteById(id);
    }

    @Transactional
    @Override
    public List<AdvertisementDTO> getAllByUserId(long userId) {
        List<Advertisement> advertisements = advertisementDAO.findAllByUserId(userId);
        return getAdvertisementList(advertisements);
    }


    @Transactional
    @Override
    public List<AdvertisementDTO> findByFilter(Long filterCountry, String filterCity) {
        String city = filterCity.toLowerCase();
        List<Advertisement> advertisementList = advertisementDAO.findByCountryAndCity(filterCountry, city);
        return getAdvertisementList(advertisementList);
    }

    @Transactional
    @Override
    public List<AdvertisementDTO> getAllFree(){
        List<Advertisement> advertisements = advertisementDAO.findAllFree();
        return getAdvertisementList(advertisements);
    }


    @Override
    public boolean checkUser(Long idAdvertisement, User user) {
        boolean checkUser = true;
        if (user != null) {
            String username = user.getUsername();
            String usernameByIdAdvertisement = findUsernameByIdAdvertisement(idAdvertisement);
            if (username.equals(usernameByIdAdvertisement)) {
                checkUser = false;
            }
        }
        return checkUser;
    }


    private List<AdvertisementDTO> getAdvertisementList(List<Advertisement> advertisements) {
        if (advertisements == null) {
            return null;
        } else {
            List<AdvertisementDTO> advertisementDTOList = new ArrayList<>();
            for (Advertisement advertisement : advertisements) {
                String city = advertisement.getAddress().getCity();
                advertisement.getAddress().setCity(city.substring(0,1).toUpperCase() + city.substring(1).toLowerCase());
                advertisementDTOList.add(advertisementDTOParser.createAdvertDTOFromDomain(advertisement));
            }
            return advertisementDTOList;
        }
    }

    private String findUsernameByIdAdvertisement(Long idAdvertisement) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        User user = advertisement.getUser();
        String username = user.getUsername();
        return username;
    }

}
