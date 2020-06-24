package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import com.bystrov.rent.domain.Reservation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

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
    public List<Advertisement> findByFilter(Long idCountry, String city, LocalDate filterArrivalDate, LocalDate filterDepartureDate) {
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
        if (filterArrivalDate != null && filterDepartureDate != null){
            Join<Advertisement, Reservation> joinTable = advertisementRoot.join("reservation", JoinType.LEFT);

            Predicate predicateArrivalDate = cb.and(
                    cb.lessThan(joinTable.get("arrivalDate"), filterArrivalDate),
                    cb.lessThan(joinTable.get("departureDate"), filterArrivalDate));

            Predicate predicateDepartureDate = cb.and(
                    cb.greaterThan(joinTable.get("arrivalDate"), filterDepartureDate),
                    cb.greaterThan(joinTable.get("departureDate"), filterDepartureDate));

            Predicate predicateIsNull = cb.and(
                    cb.isNull(joinTable.get("arrivalDate")),
                    cb.isNull(joinTable.get("departureDate")));

            predicates.add(cb.or(predicateArrivalDate, predicateDepartureDate, predicateIsNull));
            /*predicates.add(cb.or(cb.lessThanOrEqualTo(advertisementRoot.join("reservation").get("arrivalDate"), filterArrivalDay),
                    cb.lessThanOrEqualTo(advertisementRoot.join("reservation").get("departureDate"), filterDepartureDay)));*/
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Advertisement> query = em.createQuery(cq);
        return query.getResultList();
    }
}
