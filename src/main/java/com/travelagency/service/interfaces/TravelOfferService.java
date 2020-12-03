package com.travelagency.service.interfaces;

import com.travelagency.entity.TravelOffer;

import java.util.List;

public interface TravelOfferService
        extends GenericService<TravelOffer> {
    List<String> getOfferTypes();
}
