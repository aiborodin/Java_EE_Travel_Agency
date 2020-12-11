package com.travelagency.dao.interfaces;

import com.travelagency.entity.OfferType;
import com.travelagency.entity.TravelOffer;

import java.util.List;

public interface TravelOfferDao
        extends GenericDao<TravelOffer> {

    List<TravelOffer> getByType(OfferType offerType);

}
