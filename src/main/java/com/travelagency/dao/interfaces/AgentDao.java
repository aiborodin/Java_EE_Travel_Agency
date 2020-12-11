package com.travelagency.dao.interfaces;

import com.travelagency.entity.Agent;

import java.util.List;

public interface AgentDao
        extends UserDao<Agent> {

    List<Agent> findActiveAgents();

    List<Agent> findByFirstName(String firstName);

}
