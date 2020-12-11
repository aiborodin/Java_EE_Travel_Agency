package com.travelagency.service.interfaces;

import com.travelagency.entity.User;

import java.util.Optional;

public interface UserService<T extends User>
        extends GenericService<T> {
    Optional<T> authorize(String login, String password);
}
