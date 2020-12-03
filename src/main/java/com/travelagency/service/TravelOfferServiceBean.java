package com.travelagency.service;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.dao.interfaces.TravelOfferDao;
import com.travelagency.entity.TravelOffer;
import com.travelagency.service.interfaces.TravelOfferService;
import com.travelagency.service.interfaces.annotations.Loggable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named("travelOfferService")
@ApplicationScoped
@Loggable
public class TravelOfferServiceBean
        extends AbstractEntityService<TravelOffer>
        implements TravelOfferService {

    @Inject
    void setDao(TravelOfferDao travelOfferDao) {
        this.dao = travelOfferDao;
        this.items = travelOfferDao.readAll();
    }

    @Override
    public List<String> getOfferTypes() {
        return items.stream().map(TravelOffer::getOfferType).distinct().collect(Collectors.toList());
    }
}
