package com.bystrov.rent.service.impl;

import com.bystrov.rent.DTO.AdvertisementDTO;
import com.bystrov.rent.DTO.ReservationDTO;
import com.bystrov.rent.DTO.parser.AdvertisementDTOParser;
import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.ReservationDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.user.User;
import com.bystrov.rent.service.AdvertisementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
    public Page<AdvertisementDTO> findPaginated(Pageable pageable) {
        return getAdvertisementPage(pageable,advertisementDAO.findAll());
    }

    @Transactional
    @Override
    public Page<AdvertisementDTO> findPaginatedByFilter(Pageable pageable,
                                                        Long filterCountry,
                                                        String filterCity,
                                                        String arrivalDate,
                                                        String departureDay) throws ParseException {
        String city = filterCity.toLowerCase();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate arrival = StringUtils.isNotBlank(arrivalDate) ? LocalDate.parse(arrivalDate, format) : null;
        LocalDate departure = StringUtils.isNotBlank(departureDay) ? LocalDate.parse(departureDay, format) : null;
        List<Advertisement> advertisementList = advertisementDAO.findByFilter(filterCountry, city, arrival, departure);
        return getAdvertisementPage(pageable, advertisementList);
    }

    @Transactional
    @Override
    public Page<AdvertisementDTO> findPaginatedByUserId(Pageable pageable, Long userId) {
        return getAdvertisementPage(pageable, advertisementDAO.findAllByUserId(userId));
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
    public AdvertisementDTO saveAdvertisement(AdvertisementDTO advertisementDTO) {
        if(advertisementDTO == null ){
            throw new EntityNotFoundException("Advertisement not found");
        }
        Advertisement advertisement = advertisementDTOParser.createAdvertDomainFromDTO(advertisementDTO);
        advertisement.getAddress().setCity(advertisementDTO.getAddress().getCity().toLowerCase());
        advertisement.setDate(LocalDate.now());
        advertisementDAO.save(advertisement);
        return advertisementDTOParser.createAdvertDTOFromDomain(advertisement);
    }

    @Transactional
    @Override
    public void update(Long idAdvertisement) {
        Advertisement advertisement = advertisementDAO.findById(idAdvertisement);
        if(advertisement == null){
            throw new EntityNotFoundException("Advertisement not found!");
        }
        advertisementDAO.update(advertisement);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        advertisementDAO.deleteById(id);
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

    @Override
    public boolean checkByDate(ReservationDTO reservationDTO) {
        return false;
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

    private Page<AdvertisementDTO> getAdvertisementPage(Pageable pageable, List<Advertisement> advertisementList) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AdvertisementDTO> advertisementDTOListPage;
        if (advertisementList == null) {
            return null;
        } else {
            if (advertisementList.size() < startItem) {
                advertisementDTOListPage = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, advertisementList.size());
                List<AdvertisementDTO> advertisementDTOList = getAdvertisementList(advertisementList);
                advertisementDTOListPage = advertisementDTOList.subList(startItem, toIndex);
            }
            Page<AdvertisementDTO> advertisementDTOPage =
                    new PageImpl<AdvertisementDTO>(advertisementDTOListPage, PageRequest
                            .of(currentPage, pageSize), advertisementList.size());
            return advertisementDTOPage;
        }
    }



}
