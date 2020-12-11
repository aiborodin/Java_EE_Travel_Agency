package com.travelagency.dao.jpa;

import com.travelagency.dao.interfaces.TravelOfferDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Client;
import com.travelagency.entity.OfferType;
import com.travelagency.entity.TravelOffer;

import javax.ejb.Stateless;
import java.util.List;

@JPA
@Stateless
public class TravelOfferDaoImpl extends AbstractJpaDao<TravelOffer>
        implements TravelOfferDao {

    @Override
    public List<TravelOffer> getByType(OfferType offerType) {
        return entityManager
                .createQuery("SELECT offer FROM TravelOffer offer WHERE offer.offerType = :type", TravelOffer.class)
                .setParameter("type", offerType)
                .getResultList();
    }
}
