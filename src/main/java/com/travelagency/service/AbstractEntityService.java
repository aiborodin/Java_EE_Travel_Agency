package com.travelagency.service;

import com.travelagency.dao.interfaces.GenericDao;
import com.travelagency.entity.Identifiable;
import com.travelagency.service.interfaces.GenericService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public abstract class AbstractEntityService<T extends Identifiable>
        implements GenericService<T> {

    @Inject
    protected Logger logger;

    protected List<T> items;

    protected GenericDao<T> dao;

    @Override
    public Optional<T> find(int id) {
        return items.stream().filter(item -> item.getId() == id).findAny();
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = items.removeIf(item -> item.getId() == id);
        if (deleted) {
            dao.delete(id);
        }
        return deleted;
    }

    @Override
    public void update(T newItem) {
        T oldItem;
        for (int i = 0; i < items.size(); i++) {
            oldItem = items.get(i);
            if (oldItem.getId() == newItem.getId()) {
                items.set(i, newItem);
                dao.update(newItem);
                break;
            }
        }
    }

    @Override
    public void add(T item) {
        int id = dao.add(item);
        if (id != -1) {
            item.setId(id);
            items.add(item);
        }
    }

    @Override
    public List<T> getAll() {
        return items;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
