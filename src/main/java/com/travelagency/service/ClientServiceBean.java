package com.travelagency.service;

import com.travelagency.dao.interfaces.ClientDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Client;
import com.travelagency.service.interfaces.ClientService;
import com.travelagency.service.interfaces.annotations.Loggable;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Singleton
@Named("clientService")
@Loggable
public class ClientServiceBean
        extends AbstractUserService<Client> implements ClientService {

    @Inject
    void setDao(@JPA ClientDao clientDao) {
        dao = clientDao;
        items = clientDao.findAll();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return items.stream()
                .filter(u -> u.getEmail().matches(".*" + email + ".*"))
                .findAny();
    }
}
