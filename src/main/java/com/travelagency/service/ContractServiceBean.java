package com.travelagency.service;

import com.travelagency.dao.interfaces.ContractDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Contract;
import com.travelagency.service.interfaces.ContractService;
import com.travelagency.service.interfaces.annotations.Loggable;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Singleton
@Named("contractService")
@Loggable
public class ContractServiceBean
        extends AbstractEntityService<Contract>
        implements ContractService {

    @Inject
    void setDao(@JPA ContractDao contractDAO) {
        dao = contractDAO;
        items = contractDAO.findAll();
    }

}
