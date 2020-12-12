package com.travelagency.dao.jpa;

import com.travelagency.dao.interfaces.ContractDao;
import com.travelagency.dao.interfaces.annotations.JPA;
import com.travelagency.entity.Contract;

import javax.ejb.Stateless;

@JPA
@Stateless
public class ContractDaoImpl extends AbstractJpaDao<Contract>
        implements ContractDao {

}
