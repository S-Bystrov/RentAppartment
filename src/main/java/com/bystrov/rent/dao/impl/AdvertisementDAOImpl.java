package com.bystrov.rent.dao.impl;

import com.bystrov.rent.dao.AdvertisementDAO;
import com.bystrov.rent.dao.EntityDAO;
import com.bystrov.rent.domain.advertisement.Advertisement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AdvertisementDAOImpl extends EntityDAO<Advertisement> implements AdvertisementDAO {

    public AdvertisementDAOImpl() {
        setEntityClass(Advertisement.class);
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
            /*query.setParameter("paramCity", city);
            query.setParameter("paramCountry", country);*/
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
