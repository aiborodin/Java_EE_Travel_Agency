package com.travelagency.service;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Agent;
import com.travelagency.service.interfaces.AgentService;
import com.travelagency.service.interfaces.annotations.Loggable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("agentService")
@ApplicationScoped
@Loggable
public class AgentServiceBean
        extends AbstractUserService<Agent> implements AgentService {

    @Inject
    private void setDao(@JPA AgentDao agentDao) {
        dao = agentDao;
        items = dao.findAll();
    }

}
