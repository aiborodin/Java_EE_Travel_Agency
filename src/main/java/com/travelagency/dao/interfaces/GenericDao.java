package com.travelagency.dao.interfaces;

import java.util.List;

public interface GenericDao<T> {

    List<T> findAll();

    void delete(int id);

    int persist(T entity);

    void update(T entity);
}
