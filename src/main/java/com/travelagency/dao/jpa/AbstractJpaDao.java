package com.travelagency.dao.jpa;

import com.travelagency.dao.interfaces.GenericDao;
import com.travelagency.entity.Identifiable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractJpaDao<T extends Serializable & Identifiable> implements GenericDao<T> {

    @PersistenceContext(unitName = "travelAgency_PU")
    protected EntityManager entityManager;

    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AbstractJpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public void delete(int id) {
        T entity = findOne(id);
        entityManager.remove(entity);
    }

    public T findOne(int id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public int persist(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }
}
