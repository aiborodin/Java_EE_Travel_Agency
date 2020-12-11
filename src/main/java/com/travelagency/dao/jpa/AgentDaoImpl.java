package com.travelagency.dao.jpa;

import com.travelagency.dao.interfaces.AgentDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Agent;

import javax.ejb.Stateless;
import java.util.List;

@JPA
@Stateless
public class AgentDaoImpl extends AbstractJpaDao<Agent>
        implements AgentDao {

    @Override
    public List<Agent> findActiveAgents() {
        return entityManager.createNamedQuery("findActive", Agent.class).getResultList();
    }

    @Override
    public List<Agent> findByFirstName(String firstName) {
        return entityManager.createNamedQuery("findByFirstName", Agent.class)
                .setParameter("firstName", firstName).getResultList();
    }
}
