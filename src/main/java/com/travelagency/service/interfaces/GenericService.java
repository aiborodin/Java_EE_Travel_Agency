package com.travelagency.service.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public interface GenericService<T> {
    Optional<T> find(int id);

    boolean delete(int id);

    void add(T obj);

    void update(T obj);

    List<T> getAll();

    Logger getLogger();
}
