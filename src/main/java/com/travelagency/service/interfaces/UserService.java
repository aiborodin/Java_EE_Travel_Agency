package com.travelagency.service.interfaces;

import com.travelagency.dao.interfaces.GenericDao;
import com.travelagency.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserService<T extends User>
        extends GenericService<T> {
    Optional<T> authorize(String login, String password);
}
