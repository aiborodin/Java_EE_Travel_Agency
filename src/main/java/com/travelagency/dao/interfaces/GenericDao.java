package com.travelagency.dao.interfaces;

import java.util.List;

public interface GenericDao<T> {

    List<T> readAll();

    boolean delete(int id);

    int add(T obj);

    void update(T obj);
}
