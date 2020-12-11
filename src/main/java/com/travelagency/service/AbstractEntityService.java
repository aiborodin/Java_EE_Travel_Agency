package com.travelagency.service;

import com.travelagency.dao.interfaces.GenericDao;
import com.travelagency.entity.Identifiable;
import com.travelagency.service.interfaces.GenericService;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public abstract class AbstractEntityService<T extends Identifiable>
        implements GenericService<T> {

    @Inject
    protected Logger logger;

    protected List<T> items;

    protected GenericDao<T> dao;

//    protected ValidatorFactory validatorFactory;
//
//    protected Validator validator;
//
//    public AbstractEntityService() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }

//    @PreDestroy
//    void disposeValidator() {
//        validatorFactory.close();
//    }
//
//    protected String validateEntity(T entity) {
//        Set<ConstraintViolation<T>> violationSet = validator.validate(entity);
//        for (ConstraintViolation<T> violation : violationSet){
//            return (violation.getPropertyPath() + " " + violation.getMessage());
//        }
//        return null;
//    }

    @Override
    public Optional<T> find(int id) {
        return items.stream().filter(item -> item.getId() == id).findAny();
    }

    @Override
    public boolean delete(int id) {
        dao.delete(id);
        return items.removeIf(item -> item.getId() == id);
    }

    @Override
    public void update(T newItem) {
        T oldItem;
        for (int i = 0; i < items.size(); i++) {
            oldItem = items.get(i);
            if (oldItem.getId() == newItem.getId()) {
                dao.update(newItem);
                items.set(i, newItem);
                break;
            }
        }
    }

    @Override
    public void add(T item) {
        int id = dao.persist(item);
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
