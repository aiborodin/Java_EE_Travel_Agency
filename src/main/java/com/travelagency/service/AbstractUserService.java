package com.travelagency.service;

import com.travelagency.dao.interfaces.UserDao;
import com.travelagency.entity.User;
import com.travelagency.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

public abstract class AbstractUserService<T extends User> extends AbstractEntityService<T>
        implements UserService<T> {

    @Override
    public Optional<T> authorize(String login, String password) {
        return items.stream()
                .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
                .findAny();
    }
}
