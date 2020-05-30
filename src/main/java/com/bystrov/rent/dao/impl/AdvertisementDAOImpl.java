package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertisementDAOImpl extends EntityDAO<Advertisement> implements AdvertisementDAO {

    public AdvertisementDAOImpl() {
        setEntityClass(Advertisement.class);
    }

    @Override
    public List<Advertisement> findByFilter(Long idCountry, String city, LocalDate filterArrivalDay, LocalDate filterDepartureDay) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);

        Root<Advertisement> advertisementRoot = cq.from(Advertisement.class);
        List<Predicate> predicates = new ArrayList<>();
        if (idCountry != null){
            predicates.add(cb.equal(advertisementRoot.get("address").get("country").get("idCountry"), idCountry));
        }
        if (StringUtils.isNotBlank(city)){
            predicates.add(cb.equal(advertisementRoot.get("address").get("city"), city));
        }
        if (filterArrivalDay != null && filterDepartureDay != null){

            predicates.add(cb.or(cb.lessThanOrEqualTo(advertisementRoot.join("reservation").get("arrivalDate"), filterArrivalDay),
                    cb.lessThanOrEqualTo(advertisementRoot.join("reservation").get("departureDate"), filterDepartureDay)));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Advertisement> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Advertisement> findByCountryAndCity(Long idCountry, String city) {
        boolean countryCheck = idCountry != null;
        boolean cityCheck = city != null && !StringUtils.isBlank(city);
        if (countryCheck || cityCheck) {
            String cityQuery = "";
            if (cityCheck && countryCheck) {
                cityQuery = " ad.address.city = \'" + city + "\' and";
            } else {
                if(cityCheck && !countryCheck){
                    cityQuery = "ad.address.city = \'" + city + "\'";
                }
            }
            String countryQuery = "";
            if (countryCheck) {
                countryQuery = " ad.address.country.idCountry = " + idCountry;
            }
            Query query = em.createQuery("from Advertisement ad where " + cityQuery + countryQuery);
            List<Advertisement> advertisementList = (List<Advertisement>) query.getResultList();
            if(advertisementList.size() == 0){
                return null;
            } else {
                return advertisementList;
            }
        }
        return null;
    }
}
