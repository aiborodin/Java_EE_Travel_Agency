package com.travelagency.dao.jpa;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.dao.interfaces.ClientDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Agent;
import com.travelagency.entity.Client;

import javax.ejb.Stateless;

@JPA
@Stateless
public class ClientDaoImpl extends AbstractJpaDao<Client>
        implements ClientDao {

}
