package com.travelagency.dao.interfaces;

import com.travelagency.entity.User;

import java.util.List;

public interface UserDao<T extends User>
        extends GenericDao<T> {

}
